package com.coo.s.cloud.model;

public class Group extends BasicObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 154183073140207046L;
	public static String SET = "coo_group";
	/**
	 * 名称: 组描述,备用字段
	 */
	@Column
	private String name = "";
	/**
	 * 组描述：备用字段
	 */
	@Column
	private String description = "";
	/**
	 * 账号组1,记录名称和手机号.... 参见 13512210211,13512210212; 100个电话还是能存的
	 */
	@Column
	private String accounts0 = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccounts0() {
		return accounts0;
	}

	public void setAccounts0(String accounts0) {
		this.accounts0 = accounts0;
	}
}
