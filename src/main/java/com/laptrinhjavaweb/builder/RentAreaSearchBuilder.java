package com.laptrinhjavaweb.builder;

public class RentAreaSearchBuilder {
	private Long id;
	private Integer value;
	private Long buildingid;
	
	public Long getId() {
		return id;
	}
	public Integer getValue() {
		return value;
	}
	public Long getBuildingid() {
		return buildingid;
	}
	
	private RentAreaSearchBuilder(Builder builder) {
		this.id = builder.id;
		this.buildingid = builder.buildingid;
		this.value = builder.value;
	}
	
	public static class Builder{

		private Long id;
		private Integer value;
		private Long buildingid;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder setValue(Integer value) {
			this.value = value;
			return this;
		}
		
		public Builder setBuildingid(Long buildingid) {
			this.buildingid = buildingid;
			return this;
		}
		
		public RentAreaSearchBuilder build(){
			return new RentAreaSearchBuilder(this);
		}
		
	}
}
