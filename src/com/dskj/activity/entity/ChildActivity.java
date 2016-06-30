package com.dskj.activity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 亲子活动
 * 
 */
public class ChildActivity implements Serializable {

	private static final long serialVersionUID = 4333385771487368719L;
	private Integer id;
	private String institutionId;
	private String ticketType;
	private int activityType;
	private String title;
	private String name;
	private BigDecimal price;
	private BigDecimal oldPrice;
	private int saleCount;
	private String thumbImg;
	private String adverImg;
	private String shortDetail;
	private String detail;
	private String url;
	private int shareCount;
	private int loveCount;
	private int commentCount;
	private Date createTime;
	private Date beginDate;
	private Date endDate;
	private Date reserveBeginDate;
	private Date reserveEndDate;
	private Integer activityCollectId;
	private Integer activityLoveId;
	private Integer activityReserveId;

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

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public String getAdverImg() {
		return adverImg;
	}

	public void setAdverImg(String adverImg) {
		this.adverImg = adverImg;
	}

	public String getShortDetail() {
		return shortDetail;
	}

	public void setShortDetail(String shortDetail) {
		this.shortDetail = shortDetail;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
//		try {
//			this.detail = URLDecoder.decode(detail, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		this.detail = detail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getLoveCount() {
		return loveCount;
	}

	public void setLoveCount(int loveCount) {
		this.loveCount = loveCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getReserveBeginDate() {
		return reserveBeginDate;
	}

	public void setReserveBeginDate(Date reserveBeginDate) {
		this.reserveBeginDate = reserveBeginDate;
	}

	public Date getReserveEndDate() {
		return reserveEndDate;
	}

	public void setReserveEndDate(Date reserveEndDate) {
		this.reserveEndDate = reserveEndDate;
	}

	public Integer getActivityCollectId() {
		return activityCollectId;
	}

	public void setActivityCollectId(Integer activityCollectId) {
		this.activityCollectId = activityCollectId;
	}

	public Integer getActivityLoveId() {
		return activityLoveId;
	}

	public void setActivityLoveId(Integer activityLoveId) {
		this.activityLoveId = activityLoveId;
	}

	public Integer getActivityReserveId() {
		return activityReserveId;
	}

	public void setActivityReserveId(Integer activityReserveId) {
		this.activityReserveId = activityReserveId;
	}

}