package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.RentAreaDTO;

public interface IRentAreaService {
	RentAreaDTO save (RentAreaDTO rentAreaDTO);
	void delete(Long[] ids);
	List<RentAreaDTO> findById(Long id);
	RentAreaDTO update(RentAreaDTO rentAreaDTO,Long id);
}
