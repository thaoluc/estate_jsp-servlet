package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.AssignmentStaffSearchBuilder;
import com.laptrinhjavaweb.dto.AssignmentStaffDTO;


public interface IAssignmentStaffService {
	List<AssignmentStaffDTO> findAll(AssignmentStaffSearchBuilder fieldSearch);
	void delete(Long[] ids);
}
