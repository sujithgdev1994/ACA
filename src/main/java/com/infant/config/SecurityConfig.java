package com.infant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationRequestFilter authenticationRequestFilter;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/v1/user/signup", "/v1/authenticate", "/v3/api-docs", "/swagger-ui/index.html",
					"/v3/api-docs/swagger-config", "/v3/api-docs", "/swagger-ui/swagger-ui.css", "/swagger-ui/index.css",
					"/swagger-ui/swagger-ui-bundle.js", "/swagger-ui/swagger-ui-standalone-preset.js",
					"/swagger-ui/swagger-initializer.js", "/swagger-ui.html", "/swagger-ui/favicon-32x32.png", "/swagger-ui/favicon-16x16.png", "/**").permitAll().anyRequest().authenticated();
		}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.httpBasic(Customizer.withDefaults()).build();
	}

}