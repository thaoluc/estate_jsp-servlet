package com.laptrinhjavaweb.repository;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingRepository extends JpaRepository<BuildingEntity> {
	//List<BuildingEntity> findAll(Map<String, Object> properties, Pageable pageable);
}
