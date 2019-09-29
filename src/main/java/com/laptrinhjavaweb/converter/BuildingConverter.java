package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public class BuildingConverter {
	
	public BuildingDTO convertToDTO (BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
		/*
		if(buildingEntity.getBuildingArea() != null) {
			buildingDTO.setBuildingArea(String.valueOf(buildingEntity.getBuildingArea()));
		}
		if(buildingEntity.getNumberOfBasement() != null) {
			buildingDTO.setBuildingArea(String.valueOf(buildingEntity.getNumberOfBasement()));
		}
		*/
		return buildingDTO;
	}
	
}
