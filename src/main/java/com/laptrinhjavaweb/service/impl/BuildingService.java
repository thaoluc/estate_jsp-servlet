package com.laptrinhjavaweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService{

	private IBuildingRepository buildingRepository;
	private BuildingConverter buildingConverter;


	public BuildingService() {
		buildingRepository = new BuildingRepository();
		buildingConverter = new BuildingConverter();
	}
	
	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable) {
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("name", fieldSearch.getName());
		properties.put("district", fieldSearch.getDistrict());
		if(StringUtils.isNotBlank(fieldSearch.getBuildingArea())) {
			properties.put("buildingarea", Integer.parseInt(fieldSearch.getBuildingArea()));
		}
		if(StringUtils.isNotBlank(fieldSearch.getNumberOfBasement())) {
			properties.put("numberofbasement", Integer.parseInt(fieldSearch.getNumberOfBasement()));
		}
	
		//List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties, pageable);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties);
		return buildingEntities.stream()
				.map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
	}

}
