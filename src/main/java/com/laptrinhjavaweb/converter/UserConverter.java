package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

public class UserConverter {
	
	public UserDTO convertToDTO (UserEntity userEntity) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
		return userDTO;
	}
	
	public UserEntity convertToEntity (UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
		return userEntity;
	}
	
}
