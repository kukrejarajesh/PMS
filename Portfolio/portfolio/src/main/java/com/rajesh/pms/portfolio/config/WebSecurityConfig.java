package com.rajesh.pms.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.*;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	@Autowired
	DataSource dataSource;

	@SuppressWarnings("removal")
	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
			.sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//.formLogin(Customizer.withDefaults());
		.httpBasic(withDefaults());
		
			
			//.logout((logout) -> logout.permitAll());

			
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withUsername("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		 
		UserDetails admin =
				 User.withUsername("admin")
				 .password(passwordEncoder().encode("password"))
					.roles("ADMIN")
					.build();
		
		
		
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
		// userDetailsManager.createUser(user);   Commeneted as user already exist and we don't want application to fail to run
		//userDetailsManager.createUser(admin);    Commeneted as user already exist and we don't want application to fail to run
		return userDetailsManager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
}