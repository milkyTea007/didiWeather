package com.didiweather.app.model;

/**
 * 创建省份对应的实体类
 */
public class Provice {

	private int id;
	private String proviceName;
	private String proviceCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProviceName() {
		return proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}

	public String getProviceCode() {
		return proviceCode;
	}

	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}

}
