package com.party.plan.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.party.plan.auth.filter.JWTAuthenticationFilter;
import com.party.plan.auth.filter.LoginFilter;
import com.party.plan.auth.security.JWTAuthenticationEntryPoint;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JWTAuthenticationEntryPoint unauthorizedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers("/").permitAll()
		.antMatchers(HttpMethod.POST,"/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(new LoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    // Create a default account
	    auth.inMemoryAuthentication()
	        .withUser("ravi")
	        .password("password")
	        .roles("ADMIN","USER");
	    
	    auth.inMemoryAuthentication()
	        .withUser("prakash")
	        .password("password")
	        .roles("USER");
	  }
}
