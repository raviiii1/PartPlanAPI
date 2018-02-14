package com.party.plan.auth.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.party.plan.auth.model.Credentials;
import com.party.plan.auth.security.JWTSuccessHandler;
import com.party.plan.auth.security.TokenAuthenticationService;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager manager) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(manager);
		setAuthenticationSuccessHandler(new JWTSuccessHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Credentials credentials = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(),
						credentials.getPassword(),
						Collections.emptyList())
				);
	}

	/**
	 * Add the token to response on successful authentication.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		TokenAuthenticationService.addAuthenticationToken(response, authResult);
	}
}
