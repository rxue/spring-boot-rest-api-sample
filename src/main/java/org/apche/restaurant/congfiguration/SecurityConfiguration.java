package org.apche.restaurant.congfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity //!Compulsory TODO: read about this annotation
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();//In practice, this should not be disabled!
//		httpSecurity.antMatcher("/rest/private/*") //NB! start with /
//			.authorizeRequests().anyRequest().hasRole("ADMIN")
//			.and()
//			.httpBasic()
//			.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.antMatcher("/rest/**")
		.authorizeRequests().antMatchers(HttpMethod.POST).hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers(HttpMethod.PUT).hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
		.and()
		.httpBasic()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
}
