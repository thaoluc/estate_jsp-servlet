package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.entity.UserEntity;

public interface IStaffRepository extends JpaRepository<UserEntity>{
	List<UserEntity> findAll(Map<String,Object> param, UserSearchBuilder fieldSearch);
}
