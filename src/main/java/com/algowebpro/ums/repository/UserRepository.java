package com.algowebpro.ums.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algowebpro.ums.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
	Optional<User> findByUsernameWithRoles(String username);
}
