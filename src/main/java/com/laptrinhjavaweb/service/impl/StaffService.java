package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.converter.StaffConverter;
import com.laptrinhjavaweb.dto.StaffDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IStaffRepository;
import com.laptrinhjavaweb.repository.impl.StaffRepository;
import com.laptrinhjavaweb.service.IStaffService;

public class StaffService implements IStaffService{
	private StaffConverter staffConverter;
	private IStaffRepository staffRepository;
	

	public StaffService() {
		staffConverter = new StaffConverter();
		staffRepository = new StaffRepository();
	}
	
	@Override
	public List<StaffDTO> findAll(UserSearchBuilder fieldSearch) {
		
		Map<String, Object> properties = convertToMapProperties(fieldSearch);
		List<UserEntity> userEntities = staffRepository.findAll(properties,fieldSearch);
		

//		for(UserEntity staff:userEntities) {
//			if(fieldSearch.getBuildingId() == assignmentStaff.getBuildingId() ) {
//				staffDTO.setChecked("Check");
//			}else{
//				staffDTO.setChecked("");
//			}
//		}
		
		return userEntities.stream()
				.map(item -> staffConverter.convertToDTO(item)).collect(Collectors.toList());
	}
	
	private Map<String, Object> convertToMapProperties(UserSearchBuilder fieldSearch) {
		Map<String, Object> properties = new HashMap<>();

		try {
			Field[] fields = UserSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if(!field.getName().equals("buildingId")) {
					field.setAccessible(true);
					properties.put(field.getName().toLowerCase(), field.get(fieldSearch));
				}
		

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println(e.getMessage());

		}

		return properties;
	}

}
