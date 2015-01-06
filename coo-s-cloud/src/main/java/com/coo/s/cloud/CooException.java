package com.coo.s.cloud;


/**
 * 运行级错误
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 */
public class CooException extends RuntimeException {

	private static final long serialVersionUID = -626884733328969303L;
	
	public static CooException NOT_IMPLEMENTED = new CooException(
			"has not been implemented!", null);
	public static CooException DEPRECATED = new CooException(
			"has not been deprecated!", null);

	public CooException() {

	}

	/**
	 * 构造函数
	 * 
	 * @param shortMsg
	 */
	public CooException(String shortMsg) {
		this(shortMsg, null);
	}

	/**
	 * 构造函数
	 * 
	 * @param e
	 */
	public CooException(Exception e) {
		this(e.getMessage(), e);
	}

	/**
	 * 通用运行异常
	 * 
	 * @param shortMsg
	 * @param throwable
	 */
	public CooException(String shortMsg, Throwable throwable) {
		super(shortMsg,throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
