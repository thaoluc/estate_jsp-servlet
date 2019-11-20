package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.StaffDTO;
import com.laptrinhjavaweb.entity.UserEntity;

public class StaffConverter {
	public StaffDTO convertToDTO (UserEntity userEntity) {
		ModelMapper modelMapper = new ModelMapper();
		StaffDTO staffDTO = modelMapper.map(userEntity, StaffDTO.class);
		return staffDTO;
	}
	
	public UserEntity convertToEntity (StaffDTO staffDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(staffDTO, UserEntity.class);
		return userEntity;
	}
}
