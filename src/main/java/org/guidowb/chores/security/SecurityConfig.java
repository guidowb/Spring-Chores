package org.guidowb.chores.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationProvider authenticationProvider() {
		AuthenticationProvider provider = new AuthenticationProvider();
		return provider;
	}

	/**
	 * This filter injects AngularJS compatible cookies into the response whenever
	 * the CSRF token changes value. This enables use of Angular $http with APIs
	 * that have csrf() protection enabled, while minimizing the risk of tokens
	 * leaking.
	 * 
	 * @return
	 */
	@Bean
	public Filter xsrfFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
				CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
				if (csrfToken != null) {
				    Cookie tokenCookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String tokenValue = csrfToken.getToken();
					if (tokenCookie == null || tokenValue != null && !tokenValue.equals(tokenCookie.getValue())) {
						tokenCookie = new Cookie("XSRF-TOKEN", tokenValue);
						tokenCookie.setPath("/");
						response.addCookie(tokenCookie);
					}
				}
				chain.doFilter(request, response);
			}
		};
	}

	public CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.httpBasic().and()
    		.csrf().csrfTokenRepository(csrfTokenRepository()).and()
    		.authorizeRequests()
    			.antMatchers("/", "/css/**", "/js/**", "/webjars/**", "/partials/**").permitAll()
    			.anyRequest().authenticated();
    }
}