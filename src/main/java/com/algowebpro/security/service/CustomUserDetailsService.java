package com.algowebpro.security.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.algowebpro.ums.entity.User;
import com.algowebpro.ums.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		log.debug("Loading user by username or email: {}", usernameOrEmail);

		// Try to find user by username or email
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> {
			log.error("User not found with username or email: {}", usernameOrEmail);
			return new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail);
		});

		log.debug("User found: {} with {} roles", user.getUsername(), user.getRoles().size());

		return null;
	}

	private UserDetails buildUserDetails(User user) {
		Collection<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()).authorities(authorities).accountExpired(false).accountLocked(false)
				.credentialsExpired(false).disabled(!user.isEnabled()).build();
	}

}
