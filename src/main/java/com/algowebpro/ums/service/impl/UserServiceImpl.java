package com.algowebpro.ums.service.impl;

import com.algowebpro.common.exception.BadRequestException;
import com.algowebpro.common.exception.ResourceNotFoundException;
import com.algowebpro.common.utils.MappingUtil;
import com.algowebpro.ums.dto.UserDto;
import com.algowebpro.ums.entity.User;
import com.algowebpro.ums.enums.Provider;
import com.algowebpro.ums.repository.UserRepository;
import com.algowebpro.ums.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final MappingUtil mappingUtil;

	@Override
	@Transactional
	public UserDto createUser(UserDto userDto) {

		log.info("Creating new user: {}", userDto.getUsername());

		String email = userDto.getEmail();
		if (email == null || email.isBlank()) {
			throw new BadRequestException("Email is required !!");
		}

		if (existsByEmail(email)) {
			throw new BadRequestException("Email is already in use");
		}

		if (existsByUsername(userDto.getUsername())) {
			throw new BadRequestException("Username is already taken");
		}

		User user = mappingUtil.convertToEntity(userDto, User.class);

		user.setProvider(userDto.getProvider() != null ? userDto.getProvider() : Provider.LOCAL);

		User savedUser = userRepository.save(user);

		log.info("User created successfully: {}", savedUser.getUsername());

		return mappingUtil.convertToDto(savedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(String userId) {

		log.debug("Fetching user by ID: {}", userId);

		UUID uid = UUID.fromString(userId);

		User user = findUserById(uid);

		log.info("User found: {}", user.getUsername());

		return mappingUtil.convertToDto(user, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {

		log.debug("Fetching user by email: {}", email);

		User user = userRepository.findByEmail(email).orElseThrow(() -> {
			log.error("User not found with email: {}", email);
			return new ResourceNotFoundException("User not found with email: " + email);

		});

		return mappingUtil.convertToDto(user, UserDto.class);

	}

	@Override
	public UserDto getUserByUsername(String username) {
		log.debug("Fetching user by username: {}", username);

		User user = userRepository.findByUsername(username).orElseThrow(() -> {
			log.error("User not found with username: {}", username);
			return new ResourceNotFoundException("User not found with username : " + username);
		});
		return mappingUtil.convertToDto(user, UserDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> getAllUsers() {
		
		log.debug("Fetching all users");

        List<User> users = userRepository.findAll();
        
        log.info("Found {} users", users.size());
        
		List<UserDto> userDtos = mappingUtil.convertToDtoList(users, UserDto.class);

		log.debug("Total Users fetched: {}", userDtos.size());
		return userDtos;
	}

	@Override
	@Transactional
	public UserDto updateUser(String userId, UserDto userDto) {
		log.info("Updating user with ID: {}", userId);

		UUID uid = UUID.fromString(userId);

		User existingUser = findUserById(uid);

		// we are not going to update Email ID for this project;

		// Update username if provided and different
		if (userDto.getUsername() != null && !userDto.getUsername().equals(userDto.getUsername())) {
			validateUsernameAvailability(userDto.getUsername());

			existingUser.setUsername(userDto.getUsername());

			log.debug("Username updated to: {}", userDto.getUsername());
		}

		if (userDto.getProvider() != null) {
			existingUser.setProvider(userDto.getProvider());
		}

		if (userDto.getEnabled() != null) {
			existingUser.setEnabled(userDto.getEnabled());
		}

		if (userDto.getPassword() != null) {
			existingUser.setPassword(userDto.getPassword());
		}

		User updatedUser = userRepository.save(existingUser);

		log.info("User updated successfully: {}", updatedUser.getUsername());

		return mappingUtil.convertToDto(updatedUser, UserDto.class);
	}

	@Override
	@Transactional
	public void deleteUser(String userId) {
		log.info("Deleting user with ID: {}", userId);

		UUID uid = UUID.fromString(userId);

		User user = findUserById(uid);

		userRepository.delete(user);

		log.info("User {} deleted successfully", user.getUsername());

	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	private User findUserById(UUID uid) {
		return userRepository.findById(uid).orElseThrow(() -> {
			log.error("User not found with ID: {}", uid);
			return new ResourceNotFoundException("User not found with ID : " + uid);
		});
	}

	private void validateUsernameAvailability(String username) {
		if (existsByUsername(username)) {
			log.warn("Username already taken: {}", username);
			throw new BadRequestException("Username is already taken");
		}
	}

	private void validateEmailAvailability(String email) {
		if (existsByEmail(email)) {
			log.warn("Email already in use: {}", email);
			throw new BadRequestException("Email is already in use");
		}
	}
}
