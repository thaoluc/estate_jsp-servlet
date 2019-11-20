package com.laptrinhjavaweb.repository.impl;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IStaffRepository;

public class StaffRepository extends SimpleJpaRepository<UserEntity>  implements  IStaffRepository{

	@Override
	public List<UserEntity> findAll(Map<String,Object> param, UserSearchBuilder fieldSearch) {
		StringBuilder sqlSearch = new StringBuilder("SELECT DISTINCT A.id, A.fullname FROM user A ");
		if(fieldSearch.getBuildingId()!=null) {
			sqlSearch.append(" LEFT JOIN  assignmentstaff assignmentstaff ON  assignmentstaff.staffid = A.id");
		}
		sqlSearch.append(" WHERE 1=1");
		sqlSearch=this.createSQLfindAll(sqlSearch, param);
		String sqlSpecial = buildSqlSpecial(fieldSearch);
		sqlSearch.append(sqlSpecial);

		return this.findAll(sqlSearch.toString());
	}

	private String buildSqlSpecial(UserSearchBuilder fieldSearch) {
		StringBuilder result = new StringBuilder("");

		if (fieldSearch.getBuildingId() != null) {
			result.append(" AND A.status=1");
			
		}
		return result.toString();
	}
		

	

}
