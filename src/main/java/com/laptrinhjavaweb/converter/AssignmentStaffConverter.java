package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.AssignmentStaffDTO;
import com.laptrinhjavaweb.entity.AssignmentStaffEntity;

public class AssignmentStaffConverter {

	public AssignmentStaffDTO convertToDTO (AssignmentStaffEntity assignmentStaffEntity) {
		ModelMapper modelMapper = new ModelMapper();
		AssignmentStaffDTO assignmentStaffDTO = modelMapper.map(assignmentStaffEntity, AssignmentStaffDTO.class);
		return assignmentStaffDTO;
	}
	
	public AssignmentStaffEntity convertToEntity (AssignmentStaffDTO assignmentStaffDTO) {
		ModelMapper modelMapper = new ModelMapper();
		AssignmentStaffEntity assignmentStaffEntity = modelMapper.map(assignmentStaffDTO, AssignmentStaffEntity.class);
		return assignmentStaffEntity;
	}
}
