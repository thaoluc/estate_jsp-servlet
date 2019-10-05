package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
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
		
		Map<String, Object> properties = convertToMapProperties(fieldSearch);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties, pageable, fieldSearch);
		//List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties);
		return buildingEntities.stream()
				.map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
	}


	private Map<String, Object> convertToMapProperties(BuildingSearchBuilder fieldSearch) {
		
		Map<String, Object> properties = new HashMap<>();
		
			try {
				Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
				for (Field field:fields) {
					if(!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
							&& !field.getName().startsWith("areaRent") && !field.getName().equals("staffId")) {
						field.setAccessible(true);
						if(field.get(fieldSearch) instanceof String) {
							if(field.getName().equals("buildingArea") || field.getName().equals("numberOfBasement")) {
								if(field.get(fieldSearch) != null && StringUtils.isNotEmpty((String) field.get(fieldSearch))) {
									properties.put(field.getName().toLowerCase(), Integer.parseInt(fieldSearch.getBuildingArea()));
								}
							}
							
						}else {
							properties.put(field.getName().toLowerCase(), field.get(fieldSearch));
						}
					}
					
				}	 
			}catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return properties;
	}

}
