package com.laptrinhjavaweb.entity;

import java.util.Date;

import com.laptrinhjavaweb.annotation.Column;

public class BaseEntity {

	@Column (name = "id")
	private Long id;
	
	@Column (name = "createddate")
	private Date createdDate;
	
	@Column (name = "modifieddate")
	private Date modifiedDate;
	
	@Column (name = "createdby")
	private Date createdBy;
	
	@Column (name = "modifiedby")
	private Date modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Date createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Date modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
}
