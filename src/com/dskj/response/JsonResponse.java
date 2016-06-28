package com.dskj.response;

import java.io.Serializable;

public class JsonResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -122086358277827351L;
	private Boolean success = true;
	private Integer code = 000;
	private String message = "成功";
	private Object result;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
