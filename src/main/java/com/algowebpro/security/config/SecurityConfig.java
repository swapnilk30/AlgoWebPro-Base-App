package com.algowebpro.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algowebpro.security.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

	//private final CustomUserDetailsService customUserDetailsService;

/*	
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("Initializing BCrypt password encoder");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		log.info("Configuring Security Filter Chain");

		http.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						// Public endpoints
						.requestMatchers("/api/v1/**","/api/auth/**","/actuator/health").permitAll()
					

						// All other requests must be authenticated
						.anyRequest().authenticated());

		log.info("Security Filter Chain configured successfully");
		return http.build();

	}
	*/
}
