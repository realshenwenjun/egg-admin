package com.dskj.announcement.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 * 
 * @author wenjunshen
 * 
 */
public class Announcement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2007920086977720596L;
	private Integer id;
	private String institutionId;// 机构id
	private String userId;//管理员id
	private Integer typeId;
	private Integer belong;// 所属 系统或者机构,0:平台公告,1:机构公告
	private String title;
	private String content;
	private String annoImage;
	private Date createTime;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getBelong() {
		return belong;
	}
	public void setBelong(Integer belong) {
		this.belong = belong;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAnnoImage() {
		return annoImage;
	}
	public void setAnnoImage(String annoImage) {
		this.annoImage = annoImage;
	}
	

}
