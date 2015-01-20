package com.coo.s.cloud.model;

/**
 * 关注对象
 * @Author boqing.shen
 * @since 1.0.0.0
 */
public class Focus extends BasicObject {

	public static String SET = "coo_focus";

	private static final long serialVersionUID = -8259889018653740830L;
	/**
	 * 类型:Channel,系统的频道,参见ModelManager 表示Account对某一个Channel进行了关注
	 */
	public static final String TYPE_CHANNEL = "CHANNEL";
	/**
	 * 类型：公司|商号，需要实例化....，也是一个频道.... 表示Account对某一个FIRM进行了关注
	 */
	public static final String TYPE_FIRM = "FIRM";
	/**
	 * 类型:Account: 表示Account对某一个Account进行了关注
	 */
	public static final String TYPE_ACCOUNT = "ACCOUNT";
	/**
	 * 类型:Topic: 表示Account对某一个Topic进行了关注
	 */
	public static final String TYPE_TOPIC = "TOPIC";
	/**
	 * 状态：有效
	 */
	public static final String STATUS_VALID = "0";
	/**
	 * 状态：无效
	 */
	public static final String STATUS_INVALID = "1";

	@Column(name = "type", label = "关注类型")
	private String type = "";

	@Column(name = "subject", label = "被关注客体ID|Code等，依據類型而定")
	private String subject = "";

	@Column(name = "account", label = "登录账号")
	private String account = "";
	
//	@Column(name = "status", label = "状态，0:有效；1:无效")
//	private String status = STATUS_VALID;
	
	/**
	 * 根据关注状态、关注周期,建立“我”关注的Topic
	 */
	@Column(name = "period", label = "周期,单位(天)")
	private Integer period = 1;

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the period
	 */
	public Integer getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
}
