package com.laptrinhjavaweb.builder;


public class UserSearchBuilder {
	
	private Long id;
	private String userName;
	private String fullName;
	private Integer status;
	private Long buildingId;

	public Long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	
	public String getFullName() {
		return fullName;
	}
	public Integer getStatus() {
		return status;
	}
	
	public Long getBuildingId() {
		return buildingId;
	}
	private UserSearchBuilder(Builder builder) {
		this.id=builder.id;
		this.fullName=builder.fullName;
		this.status=builder.status;
		this.userName=builder.userName;
		this.buildingId=builder.buildingId;
	}
	
	public static class Builder{

		private Long id;
		private String userName;
		private String fullName;
		private Integer status;
		private Long buildingId;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}



		public Builder setUserName(String userName) {
			this.userName = userName;
			return this;
		}


		public Builder setFullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder setStatus(Integer status) {
			this.status = status;
			return this;
		}



		public Builder setBuildingId(Long buildingId) {
			this.buildingId = buildingId;
			return this;
		}



		public UserSearchBuilder build(){
			return new UserSearchBuilder(this);
		}
		
	}
}
