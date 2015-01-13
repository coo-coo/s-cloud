package com.coo.s.cloud.model;

/**
 * 作业日志：每次Job运行,都要进行记录,记录时间戳，开始执行时间,执行结果等,便于统计Job的相关信息获取
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 */

public class JobLog extends BasicObject {

	public static String SET = "coo_log";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6785637087835797157L;

	@Column(name = "name", label = "名称")
	private String name = "";

	@Column(name = "start_ts", label = "开始(执行)时间戳")
	private Long startTs = 0l;

	@Column(name = "type", label = "类别:JOB|等")
	private String type = "";

	@Column(name = "end_ts", label = "结束时间戳")
	private Long endTs = 0l;

	public static int STATUS_SUCCESS = 0; // ,"成功");
	public static int STATUS_FAIL = 1; // ,"失败");;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startTs
	 */
	public long getStartTs() {
		return startTs;
	}

	/**
	 * @param startTs
	 *            the startTs to set
	 */
	public void setStartTs(long startTs) {
		this.startTs = startTs;
	}

	/**
	 * @return the endTs
	 */
	public long getEndTs() {
		return endTs;
	}

	/**
	 * @param endTs
	 *            the endTs to set
	 */
	public void setEndTs(long endTs) {
		this.endTs = endTs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStartTs(Long startTs) {
		this.startTs = startTs;
	}

	public void setEndTs(Long endTs) {
		this.endTs = endTs;
	}

}
