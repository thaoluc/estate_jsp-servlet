package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.AssignmentStaffEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;
import com.laptrinhjavaweb.service.IAssignmentStaffService;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IRentAreaService;

public class BuildingService implements IBuildingService{

	private IBuildingRepository buildingRepository;
	private BuildingConverter buildingConverter;
	private IRentAreaService rentAreaService; 
	private IAssignmentStaffService assignmentStaffService;
	private IRentAreaRepository rentAreaRepository;


	public BuildingService() {
		buildingRepository = new BuildingRepository();
		buildingConverter = new BuildingConverter();
		rentAreaService = new RentAreaService();
		assignmentStaffService = new AssignmentStaffService();
		rentAreaRepository = new RentAreaRepository();
		
	}
	
	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable) {
		
		Map<String, Object> properties = convertToMapProperties(fieldSearch);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties, pageable, fieldSearch);
		
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
			}catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} 
	
		return properties;
	}

	@Override
	public List<BuildingDTO> findById(Long id) {
		List<BuildingEntity> buildingEntities = buildingRepository.findById(id);
		
		return buildingEntities.stream()
				.map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
	}

	
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {

		//xử lí type building
		//làm sao để tách mảng trong buildingDTO --> String trong BuildingEntity
		BuildingEntity buildingEntity = new BuildingEntity();
		StringBuilder strType= new StringBuilder("");
		String[] arrType = buildingDTO.getType();
		if(arrType.length>0) {
			List<String> typeBuilding = Arrays.asList(arrType);
			for(String type:typeBuilding) {	
				if(strType.length() > 1) {
					strType.append(",");
				}
				strType.append(type);
	
			}
		}
		//end xử lí type building
		
		
		
	    buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setType(strType.toString());
		buildingEntity.setCreatedDate(new Date());
		buildingEntity.setCreatedBy("thaoluc");
		Long id = buildingRepository.Insert(buildingEntity);
		
		//xử lí rentarea
		// tách từng số trong chuỗi thành chuỗi con --> đưa vào mảng --> parseInt
		RentAreaDTO rentareaDTO = new RentAreaDTO();
		if (buildingDTO.getRentArea() != null) {

			String strRentArea = buildingDTO.getRentArea();
			if (strRentArea.contains(",")) {
				String[] listRentArea = strRentArea.split("\\,");
				for (String rentArea : listRentArea) {
					int k = Integer.parseInt(rentArea);
					rentareaDTO.setValue(k);
					rentareaDTO.setBuildingid(id);
					rentAreaService.save(rentareaDTO);
				}
			}
		}
		// end xử lí rentarea
		
		return findById(id).get(0);
	}

	@Override
	public void delete(Long[] ids) {
		//tien dieu kien: xoa buildingid bang assignment, bang rentarea
		// xoa rentarea: 
		RentAreaEntity rentAreaEntity = new RentAreaEntity();
		if(rentAreaEntity.getBuildingId() != null) {
			for (long id : ids) {
				if(rentAreaEntity.getBuildingId() == id) {
					rentAreaService.delete(ids);
				}
			}
		}
		//end  xoa rentarea
		
		//xoa buildingid bang assignmentstaff
		AssignmentStaffEntity assignmentStaffEntity = new AssignmentStaffEntity();
		if(assignmentStaffEntity.getBuildingId() != null) {
			for(long id:ids ) {
				if(assignmentStaffEntity.getBuildingId() == id) {
					assignmentStaffService.delete(ids);
				}
			}
		}
		//end xoa buildingid bang assignmentstaff
		
		for (long id : ids) {
			buildingRepository.delete(id);
		}

	}


	@Override
	public BuildingDTO update(BuildingDTO buildingDTO, Long id) {
		BuildingEntity buildingEntity = new BuildingEntity();

		//xử lí update type
		StringBuilder strType= new StringBuilder("");
		String[] arrType = buildingDTO.getType();
		
		if(arrType.length > 0) {	//nếu có update type
			List<String> typeBuilding = Arrays.asList(arrType);
			for(String type:typeBuilding) {	
				if(strType.length() > 1) {
					strType.append(",");
				}
				strType.append(type);
	
			}
			buildingEntity = buildingConverter.convertToEntity(buildingDTO);
			buildingEntity.setType(strType.toString());
		}
		
		if(arrType.length == 0) {	//nếu k update type
			List<BuildingEntity> buildingEntities = buildingRepository.findById(id);
			buildingEntity = buildingEntities.get(0);
			String type = buildingEntity.getType();
			
			buildingEntity = buildingConverter.convertToEntity(buildingDTO);
			buildingEntity.setType(type);
		}
		//end xử lí update type
		buildingRepository.update(buildingEntity,id);
		
		/*
		//xử lí update rentarea
		RentAreaDTO rentAreaDTO = new RentAreaDTO();
		if (buildingDTO.getRentArea() != null) {
			List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findById(id);
			RentAreaEntity rentAreaEntity = rentAreaEntities.get(0);
			if(rentAreaEntity.getBuildingId() == id) {
				List<Integer> listRentAreaEntity = new ArrayList<>();
				listRentAreaEntity.add(rentAreaEntity.getValue());
				
				String strRentArea = buildingDTO.getRentArea();
				if(strRentArea.length() > listRentAreaEntity.size()) {
					if (strRentArea.contains(",")) {
						String[] listRentArea = strRentArea.split("\\,");
						for (String rentArea : listRentArea) {
							int k = Integer.parseInt(rentArea);
							rentAreaDTO.setValue(k);
							rentAreaDTO.setBuildingid(id);
							rentAreaService.save(rentAreaDTO);
						}
					}
				}else if(strRentArea.length() == listRentAreaEntity.size()) {
					rentAreaService.update(rentAreaDTO, );
				}
			}
			
			
			
		}
		//end xử lí update rentarea*/
		
		return findById(id).get(0);
		
		
	}


}

	
