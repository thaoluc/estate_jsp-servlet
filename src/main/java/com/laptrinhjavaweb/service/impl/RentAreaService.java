package com.laptrinhjavaweb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;

public class RentAreaService implements IRentAreaService{

	private IRentAreaRepository rentAreaRepository;
	private RentAreaConverter rentAreaConverter;


	public RentAreaService() {
		rentAreaRepository = new RentAreaRepository();
		rentAreaConverter = new RentAreaConverter();
	}
	

	public RentAreaDTO save(RentAreaDTO rentareaDTO) {
		
		RentAreaEntity rentAreaEntity = rentAreaConverter.convertToEntity(rentareaDTO);
		Long id = rentAreaRepository.Insert(rentAreaEntity);

		return findById(id).get(0);
	}


	@Override
	public void delete(Long[] ids) {
		for (long id : ids) {
			rentAreaRepository.delete(id);
		}
	}


	@Override
	public List<RentAreaDTO> findById(Long id) {
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findById(id);
		return rentAreaEntities.stream()
				.map(item -> rentAreaConverter.convertToDTO(item)).collect(Collectors.toList());
	}


	@Override
	public RentAreaDTO update(RentAreaDTO rentAreaDTO, Long id) {
		//xử lí k update buildingID
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findById(id);
		Long buildingId = null;
		if(rentAreaDTO.getBuildingid() == null) {
			buildingId = rentAreaEntities.get(0).getBuildingId();
		}// end xử lí k update buildingID
		RentAreaEntity rentAreaEntity = rentAreaConverter.convertToEntity(rentAreaDTO);
		rentAreaEntity.setBuildingId(buildingId); 
		rentAreaRepository.update(rentAreaEntity,id);
		return findById(id).get(0);
	}
}
