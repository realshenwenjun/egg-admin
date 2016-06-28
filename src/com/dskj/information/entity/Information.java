package com.dskj.information.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Information implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7042622123426331850L;
	private Integer id;
	private String userId;
	private String title;
	private String context;
	private Date createTime;
	/************************************/
	private int loveCount;
    private int collectCount;
    private int commentCount;
    
    private Integer loveId;
    private Integer collectId;
    private List<String> imgUrls;
    
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLoveCount() {
		return loveCount;
	}
	public void setLoveCount(int loveCount) {
		this.loveCount = loveCount;
	}
	public int getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getLoveId() {
		return loveId;
	}
	public void setLoveId(Integer loveId) {
		this.loveId = loveId;
	}
	public Integer getCollectId() {
		return collectId;
	}
	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}
	public List<String> getImgUrls() {
		return imgUrls;
	}
	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
