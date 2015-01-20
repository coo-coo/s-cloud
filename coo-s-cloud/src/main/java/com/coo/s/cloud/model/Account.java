package com.coo.s.cloud.model;

/**
 * 账号信息:社会化信息,支持BAIDU、WEIXIN等平台第三方接入
 * 
 * @description
 * @author boqing.shen
 * @since 1.0.0.0
 */

public class Account extends BasicObject {

	public static String SET = "coo_account";
	
	@Column(name = "uid", label = "社会化ID,唯一,不采用mongo_id,便于迁移")
	private String uid = "";

	@Column(name = "source", label = "社会化资源来源:LOCAL(参见account)|BAIDU|WEIXIN...")
	private String source = "LOCAL";

	// @Column(name = "account", label = "无账号概念，采用mobile、email等代替")
	// private String account = "";

	@Column(name = "mobile", label = "手机号")
	private String mobile = "";

	@Column(name = "email", label = "邮件号")
	private String email = "";

	@Column(name = "password", label = "密码,需加密")
	private String password = "";

	@Column(name = "type", label = "账号类型:0：一般账号；1：系统账号；2：管理员帐号；3：测试账号")
	private String type = "0";

	@Column(name = "gender", label = "性别:0：男；1：女；9：未填")
	private String gender = "9";

//	@Column(name = "status", label = "账号状态")
//	private String status = STATUS_VALID;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4421956633636541182L;

	// 账号类型：一般账号
	public static final String TYPE_COMMON = "0";
	// 账号类型：系统账号
	public static final String TYPE_SYSTEM = "1";
	// 账号类型：管理员账号
	public static final String TYPE_ADMIN = "2";
	// 账号类型：测试账号
	public static final String TYPE_TEST = "3";
	// 账号状态：已注册
	public static final String STATUS_VALID = "1";
	// 账号状态：已锁定
	public static final String STATUS_LOCKED = "5";
	// 账号状态：已删除
	public static final String STATUS_DELETED = "9";

	/**
	 * 构造函数
	 */
	public Account(String mobile, String type) {
		this.mobile = mobile;
		this.type = type;
		this.status = STATUS_VALID;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
