package com.coo.s.cloud.model;

/**
 * 通讯薄信息，M端进行存储
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 */
public class Contact extends BasicObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4794341824964936905L;

	public static String SET = "coo_contact";

	/**
	 * 通讯薄名称:(通讯薄获得)
	 */
	@Column
	private String name = "";
	/**
	 * 通讯薄号码 TODO 属于手机号码才进数据库 (通讯薄获得)
	 */
	@Column
	private String mobile = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
