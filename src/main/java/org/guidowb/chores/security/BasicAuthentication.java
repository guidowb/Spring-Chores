package org.guidowb.chores.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BasicAuthentication {

	private String username;
	private String password;
	
	private BasicAuthentication(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() { return username; }
	public String getPassword() { return password; }

    @SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public static class LoginException extends RuntimeException {
    	public LoginException(String message) {
    		super(message);
    	}
    }

	public static BasicAuthentication getAuthentication(HttpServletRequest request) {

		String auth = request.getHeader("authorization");
		if (auth == null) throw new LoginException("Must supply an authorization header");
		if (!auth.startsWith("Basic ")) throw new LoginException("Only supports Basic authorization");
		auth = auth.substring(6);
		auth = new String(Base64.getDecoder().decode(auth), StandardCharsets.UTF_8);
		String[] fields = auth.split(":");
		if (fields.length != 2) throw new LoginException("Invalid authorization string");

		return new BasicAuthentication(fields[0], fields[1]);
	}
}
