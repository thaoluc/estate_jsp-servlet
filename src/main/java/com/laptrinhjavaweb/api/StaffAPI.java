package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.AssignmentStaffSearchBuilder;
import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.dto.AssignmentStaffDTO;
import com.laptrinhjavaweb.dto.StaffDTO;
import com.laptrinhjavaweb.service.IAssignmentStaffService;
import com.laptrinhjavaweb.service.IStaffService;
import com.laptrinhjavaweb.service.impl.AssignmentStaffService;
import com.laptrinhjavaweb.service.impl.StaffService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = {"/api-staff"})
public class StaffAPI extends HttpServlet {

	private static final long serialVersionUID = 1632392199018964505L;

	private IStaffService staffService = new StaffService();
	private IAssignmentStaffService assignmentStaffService = new AssignmentStaffService(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		AssignmentStaffDTO assignmentStaffDTO= FormUtil.toModel(AssignmentStaffDTO.class, request);
		AssignmentStaffSearchBuilder  fieldSearch = new AssignmentStaffSearchBuilder.Builder()
														.setBuildingId(assignmentStaffDTO.getBuildingId())
														.build();
		
		List<AssignmentStaffDTO> assignmentStaff = assignmentStaffService.findAll(fieldSearch);

		//----------------------------------------
		StaffDTO staffDTO = FormUtil.toModel(StaffDTO.class, request);
		UserSearchBuilder fieldSearchSaff = new UserSearchBuilder.Builder().setBuildingId(staffDTO.getBuildingId()).build();
		
		List<StaffDTO> allStaff = staffService.findAll(fieldSearchSaff);
		
		
		//ktra check cho nhung staff có building id can tim
		if(!allStaff.isEmpty()) {
			List<Long> listStaffId = new ArrayList<>();
			AssignmentStaffDTO as = new AssignmentStaffDTO();
			for (int i = 0; i < assignmentStaff.size(); i++) {
				as = assignmentStaff.get(i);
 
				// tạo 1 mảng chứa id của staff đc assign vs building đó
				 listStaffId.add(as.getStaffId());
			}
			for(int i =0; i<allStaff.size();i++) {
				StaffDTO staff=allStaff.get(i);
				
				if(listStaffId.contains(staff.getId())) {
					staff.setChecked("Check");
				}else {
					staff.setChecked("");
				}			
				
			}
		}
		//end ktra check cho nhung staff có building id can tim

		mapper.writeValue(response.getOutputStream(), allStaff);	
	}
}
