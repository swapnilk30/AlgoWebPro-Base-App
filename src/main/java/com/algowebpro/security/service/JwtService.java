package com.algowebpro.security.service;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpirationMs;

	@Value("${jwt.refresh-expiration}")
	private long refreshExpirationMs;

	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * Generate JWT token from Authentication object
	 */
	public String generateToken(Authentication authentication) {
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

		String roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		/*
		String token = Jwts.builder().subject(Long.toString(userPrincipal.getId()))
				.claim("username", userPrincipal.getUsername()).claim("email", userPrincipal.getEmail())
				.claim("roles", roles).issuedAt(now).expiration(expiryDate).signWith(getSigningKey()).compact();
		*/

		log.debug("Generated JWT token for user: {}", userPrincipal.getUsername());
		return null;
	}

}
