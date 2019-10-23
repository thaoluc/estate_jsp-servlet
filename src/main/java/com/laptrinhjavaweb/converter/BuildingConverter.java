package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public class BuildingConverter {
	
	public BuildingDTO convertToDTO (BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
		return buildingDTO;
	}
	
	public BuildingEntity convertToEntity (BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
		return buildingEntity;
	}
	
}
