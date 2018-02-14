package com.party.plan.auth.security;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	private TokenAuthenticationService() {
	}

	private static final String HEADER_NAME = "Authentication";
	private static final String SECRET = "raviprakash";
	private static final String PREFIX = "Bearer ";
	private static final long EXPIRATIONTIME = 864000000;
	private static final String ROLES = "roles";
	private static final String SEPARATOR = ",";

	public static Authentication getAuthentication(HttpServletRequest request) {
		String rawToken = request.getHeader(HEADER_NAME);
		if (rawToken != null) {
			Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(rawToken.replace(PREFIX, "")).getBody();
			Collection<? extends GrantedAuthority> gAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList((String) body.get((ROLES)));
			return body.getSubject() != null
					? new UsernamePasswordAuthenticationToken(body.getSubject(), null, gAuthorities) : null;
		}
		return null;
	}

	public static void addAuthenticationToken(HttpServletResponse response, Authentication authentication) {

		response.setHeader(HEADER_NAME, PREFIX + generateJWTToken(authentication));
	}

	private static String generateJWTToken(Authentication authentication) {
		Claims claims = Jwts.claims().setSubject(authentication.getName());
		claims.put(ROLES, getCommaSeparatedAuthorities(authentication.getAuthorities()));
		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	private static String getCommaSeparatedAuthorities(Collection<? extends GrantedAuthority> authorities) {
		StringBuilder commaSeparatedRoles = new StringBuilder();

		for (GrantedAuthority gAuth : authorities) {
			commaSeparatedRoles.append(gAuth.getAuthority()).append(SEPARATOR);
		}
		commaSeparatedRoles.deleteCharAt(commaSeparatedRoles.length() - 1);
		return commaSeparatedRoles.toString();
	}

}
