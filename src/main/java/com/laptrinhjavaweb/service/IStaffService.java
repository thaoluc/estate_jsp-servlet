package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.dto.StaffDTO;

public interface IStaffService {
	List<StaffDTO> findAll(UserSearchBuilder fieldSearch);

}
