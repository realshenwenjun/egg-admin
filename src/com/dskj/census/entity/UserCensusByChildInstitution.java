package com.dskj.census.entity;

import java.io.Serializable;

public class UserCensusByChildInstitution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8370215078954667416L;
	private String institutionName;
	private int userCount;
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
}
