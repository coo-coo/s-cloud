package com.coo.s.cloud.model;

/**
 * 反馈信息:可能有多个应用
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 */

public class Feedback extends BasicObject {

	private static final long serialVersionUID = -3976410079555818395L;

	public static String SET = "coo_feedback";

	@Column(name = "note", label = "反馈信息")
	private String note = "";

	@Column(name = "app_version", label = "应用版本")
	private String app_version = "";

	@Column(name = "app_name", label = "应用名称")
	private String app_name = "";

//	@Column(name = "status", label = "反馈状态")
//	private String status = STATUS_UNSOLVED;

	// 反馈状态:未处理
	public static String STATUS_UNSOLVED = "0";
	// 反馈状态:已处理
	public static String STATUS_SOLVED = "1";

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

}
