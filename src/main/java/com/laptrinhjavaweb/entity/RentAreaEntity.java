package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;

@Entity
@Table(name="rentarea")
public class RentAreaEntity extends BaseEntity{

	
		
		@Column(name="value")
		private Integer value;
		
		@Column(name="buildingid")
		private Long buildingId;

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public Long getBuildingId() {
			return buildingId;
		}

		public void setBuildingId(Long buildingId) {
			this.buildingId = buildingId;
		}
		
		
}
