package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.builder.AssignmentStaffSearchBuilder;
import com.laptrinhjavaweb.converter.AssignmentStaffConverter;
import com.laptrinhjavaweb.dto.AssignmentStaffDTO;
import com.laptrinhjavaweb.entity.AssignmentStaffEntity;
import com.laptrinhjavaweb.repository.IAssignmentStaffRepository;
import com.laptrinhjavaweb.repository.impl.AssignmentStaffRepository;
import com.laptrinhjavaweb.service.IAssignmentStaffService;

public class AssignmentStaffService implements IAssignmentStaffService {
	
	private AssignmentStaffConverter assignmentStaffConverter;
	private IAssignmentStaffRepository assignmentStaffRepository;
	
	public AssignmentStaffService() {
		assignmentStaffConverter = new AssignmentStaffConverter();
		assignmentStaffRepository = new AssignmentStaffRepository();
	}

	@Override
	public List<AssignmentStaffDTO> findAll(AssignmentStaffSearchBuilder fieldSearch) {
		Map<String, Object> properties = convertToMapProperties(fieldSearch);
		List<AssignmentStaffEntity> assignmentStaffEntity = assignmentStaffRepository.findAll(properties);
		return assignmentStaffEntity.stream()
				.map(item -> assignmentStaffConverter.convertToDTO(item)).collect(Collectors.toList());
	}

	private Map<String, Object> convertToMapProperties(AssignmentStaffSearchBuilder fieldSearch) {
		Map<String, Object> properties = new HashMap<>();

		try {
			Field[] fields = AssignmentStaffSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				
					field.setAccessible(true);
					properties.put(field.getName().toLowerCase(), field.get(fieldSearch));

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println(e.getMessage());

		}

		return properties;
	}

	@Override
	public void delete(Long[] ids) {
		for (long id : ids) {
			assignmentStaffRepository.delete(id);
		}

	}

	
	
}
