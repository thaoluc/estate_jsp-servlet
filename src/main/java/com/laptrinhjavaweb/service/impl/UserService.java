package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;
import com.laptrinhjavaweb.repository.impl.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService {

	private UserConverter userConverter;
	private IUserRepository userRepository;
	

	public UserService() {
		userConverter = new UserConverter();
		userRepository = new UserRepository();
	}
	
	@Override
	public UserDTO save(UserDTO userDTO) {	
		UserEntity userEntity = userConverter.convertToEntity(userDTO);
		return userConverter.convertToDTO(userRepository.save(userEntity));
		
	}

}
