package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingService {
	List<BuildingDTO> findAll(BuildingSearchBuilder fielSearch, Pageable pageable);
	List<BuildingDTO> findById(Long id);
	BuildingDTO save(BuildingDTO buildingDTO);
	void delete(Long[] ids);
	BuildingDTO update(BuildingDTO buildingDTO,Long id);
}
