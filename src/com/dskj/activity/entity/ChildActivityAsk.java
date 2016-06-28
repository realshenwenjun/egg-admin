package com.dskj.activity.entity;

import java.io.Serializable;
import java.util.Date;

public class ChildActivityAsk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3033105346468048945L;
	private Integer id;
	private String userId;
	private String userName;
	private String userPhoto;
	private int sex;
	private String context;
	private Integer activityId;
	private Date createTime;
	private ChildActivityAsk answer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public ChildActivityAsk getAnswer() {
		return answer;
	}
	public void setAnswer(ChildActivityAsk answer) {
		this.answer = answer;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	
	

}
