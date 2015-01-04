package com.coo.s.cloud.model;

/**
 * 账号信息,社会化信息,支持BAIDU、WEIXIN等平台第三方接入
 * 
 * @description
 * @author boqing.shen
 * @since 1.0.0.0
 */

public class Account extends BasicObject {

	@Column(name = "social_uid", label = "社会化ID")
	private String social_uid = "";

	@Column(name = "social_src", label = "社会化资源来源:LOCAL(参见account)|BAIDU|WEIXIN...")
	private String social_src = "LOCAL";
	
	@Column(name = "social_name", label = "社会化资源名称")
	private String social_name = "LOCAL";
	
	@Column(name = "account", label = "账号,本系统账号")
	private String account = "";

	@Column(name = "mobile", label = "手机号")
	private String mobile = "";

	@Column(name = "password", label = "密码")
	private String password = "";

	@Column(name = "type", label = "账号类型:0：一般账号；1：系统账号；2：管理员帐号；3：测试账号")
	private String type = "0";

	@Column(name = "gender", label = "性别:0：男；1：女；9：未填")
	private Integer gender = -1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4421956633636541182L;
	/**
	 * 账号类型：一般账号
	 */
	public static final String TYPE_COMMON = "0";
	/**
	 * 账号类型：系统账号
	 */
	public static final String TYPE_SYSTEM = "1";
	/**
	 * 账号类型：管理员账号
	 */
	public static final String TYPE_ADMIN = "2";
	/**
	 * 账号类型：测试账号
	 */
	public static final String TYPE_TEST = "3";

	public static int STATUS_VALID = 1; // 已注册
	public static int STATUS_LOCKED = 5; // 已锁定
	public static int STATUS_DELETED = 9; // 已删除

	/**
	 * 构造函数
	 */
	public Account(String mobile, String type) {
		this.mobile = mobile;
		this.type = type;
	}

	/**
	 * 构造函数
	 */
	public Account(String mobile) {
		this(mobile, Account.TYPE_COMMON);
	}

	/**
	 * 构造函数
	 */
	public Account() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSocial_uid() {
		return social_uid;
	}

	public void setSocial_uid(String social_uid) {
		this.social_uid = social_uid;
	}

	public String getSocial_src() {
		return social_src;
	}

	public void setSocial_src(String social_src) {
		this.social_src = social_src;
	}

	public String getSocial_name() {
		return social_name;
	}

	public void setSocial_name(String social_name) {
		this.social_name = social_name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
}
