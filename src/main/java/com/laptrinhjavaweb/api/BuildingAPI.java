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
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-building"})
public class BuildingAPI extends HttpServlet {

	private static final long serialVersionUID = 6536168112078931469L;
	private IBuildingService buildingService = new BuildingService();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		BuildingDTO buildingDTO =  HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);	//parse dl tu json sang dto
		buildingDTO = buildingService.save(buildingDTO);
		
		//xử lí hiển thị type of building
		
		mapper.writeValue(response.getOutputStream(), buildingDTO);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("application/json");	//server tra ve cho client kdl json
		
		BuildingDTO building = FormUtil.toModel(BuildingDTO.class, request);
		
		BuildingSearchBuilder fieldSearch = new BuildingSearchBuilder.Builder().setName(building.getName()).setDistrict(building.getDistrict())
																.setBuildingArea(building.getBuildingArea()).setNumberOfBasement(building.getNumberOfBasement())
																.setStreet(building.getStreet()).setWard(building.getWard())
																.setBuildingTypes(building.getType())
																.setAreaRentFrom(building.getAreaRentFrom()).setAreaRentTo(building.getAreaRentTo())
																.setCostRentFrom(building.getCostRentFrom()).setCostRentTo(building.getCostRentTo())
																.setStaffId(building.getStaffId())
																.build();
		Pageable pageable = new PageRequest(building.getPage(),building.getLimit());
		List<BuildingDTO> buildings = buildingService.findAll(fieldSearch, pageable);
		
		//xử lí hiển thị type of building
		
		mapper.writeValue(response.getOutputStream(), buildings);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("application/json");	
		
		BuildingDTO building = FormUtil.toModel(BuildingDTO.class, request);
		BuildingDTO building1 =  HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		
		building1 = buildingService.update(building1, building.getId());
		
		mapper.writeValue(response.getOutputStream(), building1);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingService.delete(buildingDTO.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");

	}
}
