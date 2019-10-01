package com.laptrinhjavaweb.repository.impl;

import java.util.Arrays;
import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.paging.Pageable;

public class BuildingRepository extends SimpleJpaRepository<BuildingEntity> implements IBuildingRepository  {

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params,Pageable pageable, BuildingSearchBuilder fieldSearch) {
		String sqlSpecial = buildSqlSpecial(fieldSearch);
		return this.findAll(params, pageable, sqlSpecial);
	}
	
	private String buildSqlSpecial(BuildingSearchBuilder fieldSearch) {
		StringBuilder result = new StringBuilder("");
		if(StringUtils.isNotBlank(fieldSearch.getCostRentFrom())) {
			result.append(" AND A.costrent >= "+fieldSearch.getCostRentFrom()+"");
		}
		if(StringUtils.isNotBlank(fieldSearch.getCostRentTo())) {
			result.append(" AND A.costrent <= "+fieldSearch.getCostRentTo()+"");
		}
		if(fieldSearch.getBuildingTypes().length >=0) {
			result.append(" AND (");
			//JAVA 7
		/*	int i=0;
			for(String item : fieldSearch.getBuildingTypes()) {
				if(i==0) {
					result.append("A.type like '%"+item+"%'");
				}else
				{
					result.append("OR A.type like '%"+item+"%'");
				}
				i++;
			}*/
			
			//java 8	
			result.append("A.type like '%"+fieldSearch.getBuildingTypes()[0]+"%'");
			Arrays.stream(fieldSearch.getBuildingTypes()).filter(item -> !item.equals(fieldSearch.getBuildingTypes()[0])).forEach(item -> result.append(" OR A.type like '%"+item+"%'"));
			result.append(")");
		}
		if(StringUtils.isNotBlank(fieldSearch.getAreaRentFrom()) || StringUtils.isNotBlank(fieldSearch.getAreaRentTo())) {
			result.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id");
			if(fieldSearch.getAreaRentFrom() != null) {
				result.append(" AND ra.value >= "+fieldSearch.getAreaRentFrom()+"");
			}
			if(fieldSearch.getAreaRentTo() != null) {
				result.append(" AND ra.value <= "+fieldSearch.getAreaRentTo()+"");
			}
			result.append("))");
		}
		
		return result.toString();
	}
}
