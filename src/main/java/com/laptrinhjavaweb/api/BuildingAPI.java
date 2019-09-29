package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = {"/api-building"})
public class BuildingAPI extends HttpServlet {

	private static final long serialVersionUID = 7791882872114240785L;
	
	private IBuildingService buildingService = new BuildingService();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("application/json");	//server tra ve cho client kdl json
		
		BuildingDTO building = FormUtil.toModel(BuildingDTO.class, request);
		
		BuildingSearchBuilder fieldSearch = new BuildingSearchBuilder.Builder().setName(building.getName()).setDistrict(building.getDistrict())
																.setBuildingArea(building.getBuildingArea()).setNumberOfBasement(building.getNumberOfBasement())
																.build();
		Pageable pageable = new PageRequest(building.getPage(),building.getLimit());
		List<BuildingDTO> buildings = buildingService.findAll(fieldSearch, pageable);
		
		mapper.writeValue(response.getOutputStream(), buildings);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		
	}
}
