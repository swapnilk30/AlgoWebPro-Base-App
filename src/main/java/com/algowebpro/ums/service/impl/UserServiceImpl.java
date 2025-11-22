package com.algowebpro.ums.service.impl;

import com.algowebpro.common.exception.BadRequestException;
import com.algowebpro.common.utils.MappingUtil;
import com.algowebpro.ums.dto.UserDto;
import com.algowebpro.ums.entity.User;
import com.algowebpro.ums.enums.Provider;
import com.algowebpro.ums.repository.UserRepository;
import com.algowebpro.ums.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final MappingUtil mappingUtil;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		log.info("Creating new user: {}", userDto.getUsername());
		
		String email = userDto.getEmail();
		if(email == null || email.isBlank()) {
			throw new BadRequestException("Email is required !!");
		}
		
		if (existsByEmail(email)) {
            throw new BadRequestException("Email is already in use");
        }
		
		if (existsByUsername(userDto.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }
		
		User user = mappingUtil.convertToEntity(userDto, User.class);
		
		user.setProvider(userDto.getProvider()!=null ? userDto.getProvider() : Provider.LOCAL);

		User savedUser = userRepository.save(user);
		
		log.info("User created successfully: {}", savedUser.getUsername());
		
		return mappingUtil.convertToDto(savedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
}
