package com.algowebpro.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.algowebpro.scm.service.CustomScmUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final CustomScmUserDetailsService customScmUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("scm.config.SecurityConfig() : Initializing BCrypt password encoder");
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        log.info("scm.config.SecurityConfig() : Configuring DaoAuthenticationProvider");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customScmUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring Security Filter Chain");

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/scm-user/**").authenticated();
            auth.anyRequest().permitAll();

        });

        http.formLogin(formLogin -> {
            formLogin.loginPage("/scm/login");
            formLogin.loginProcessingUrl("/scm/authenticate");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            formLogin.successForwardUrl("/scm-user/dashboard");
            formLogin.defaultSuccessUrl("/scm-user/dashboard", true);
        } );

        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(logoutForm -> {
            logoutForm.logoutUrl("/logout");
        });

       

        return http.build();
    }

}
