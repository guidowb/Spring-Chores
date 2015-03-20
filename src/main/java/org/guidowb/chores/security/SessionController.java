package org.guidowb.chores.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

	@RequestMapping(method=RequestMethod.POST)
	public void login() {}

	@RequestMapping(method=RequestMethod.DELETE)
	public void logout(HttpServletRequest request) throws ServletException {
		request.logout();
	}

	@RequestMapping(method=RequestMethod.GET)
	public void info() {}
}
