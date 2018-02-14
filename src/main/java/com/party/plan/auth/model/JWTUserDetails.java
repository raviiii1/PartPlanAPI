package com.party.plan.auth.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String stringToken;
	private Collection<? extends GrantedAuthority> authorities;

	public JWTUserDetails(String username, String password, String stringToken, List<GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.stringToken = stringToken;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getStringToken() {
		return stringToken;
	}

}
