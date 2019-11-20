package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;

public class RentAreaConverter {
	public RentAreaDTO convertToDTO (RentAreaEntity rentAreaEntity) {
		ModelMapper modelMapper = new ModelMapper();
		RentAreaDTO rentareaDTO = modelMapper.map(rentAreaEntity, RentAreaDTO.class);
		return rentareaDTO;
	}
	
	public RentAreaEntity convertToEntity (RentAreaDTO rentareaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		RentAreaEntity rentAreaEntity = modelMapper.map(rentareaDTO, RentAreaEntity.class);
		return rentAreaEntity;
	}
}
