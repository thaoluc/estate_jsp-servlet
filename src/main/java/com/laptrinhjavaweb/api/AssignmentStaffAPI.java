package com.laptrinhjavaweb.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-assignment-staff"})
public class AssignmentStaffAPI extends HttpServlet {

	private static final long serialVersionUID = 8591020830554218916L;
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

	/*	BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingService.delete(buildingDTO.getIds());*/
		
		mapper.writeValue(response.getOutputStream(), "{}");
		
		
	}

}
