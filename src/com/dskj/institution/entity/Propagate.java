package com.dskj.institution.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构宣传图片
 * @author wenjunshen
 *
 */
public class Propagate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9204331721414939332L;
	private Integer id;
	private String institutionId;
	private String title;
	private String propagate;
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPropagate() {
		return propagate;
	}
	public void setPropagate(String propagate) {
		this.propagate = propagate;
	}
	
}
