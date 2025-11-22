package com.algowebpro.ums.service;

import java.util.List;
import java.util.UUID;

import com.algowebpro.ums.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	
	
	/**
     * Get user by ID
     */
    UserDto getUserById(String userId);

	/**
	 * Get user by email
	 */
	UserDto getUserByEmail(String email);

	/**
	 * Get user by username
	 */
	UserDto getUserByUsername(String username);

	/**
	 * Get all users without pagination
	 *
	 * @return List of UserDto
	 */
	List<UserDto> getAllUsers();

	/**
	 * Update user profile
	 */
	UserDto updateUser(String userId, UserDto userDto);
	

    /**
     * Delete user
     *
     * @param userId user ID
     */
    void deleteUser(String userId);
    
    
    boolean existsByEmail(String email);
    
    boolean existsByUsername(String username);
}
