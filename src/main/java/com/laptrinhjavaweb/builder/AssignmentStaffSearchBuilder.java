package com.laptrinhjavaweb.builder;

public class AssignmentStaffSearchBuilder {
	private Long buildingId;
	private Long staffId;
	
	public Long getBuildingId() {
		return buildingId;
	}
	public Long getStaffId() {
		return staffId;
	}
	
	private AssignmentStaffSearchBuilder(Builder builder) {
		this.buildingId = builder.buildingId;
		this.staffId = builder.staffId;
	}
	
	public static class Builder{

		private Long buildingId;
		private Long staffId;

		

		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}



		public Builder setBuildingId(Long buildingId) {
			this.buildingId = buildingId;
			return this;
		}



		public AssignmentStaffSearchBuilder build(){
			return new AssignmentStaffSearchBuilder(this);
		}
		
	}
	
}
