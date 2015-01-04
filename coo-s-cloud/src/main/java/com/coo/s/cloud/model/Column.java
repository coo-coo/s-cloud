package com.coo.s.cloud.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库
 * 
 * @description
 * @author boqing.shen
 * @since 1.0.0.0
 */

@Documented
// 指定目标, 必须包含方法
@Target({ ElementType.FIELD })
// 设置保持性
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Column {

	/**
	 * 数据库字段名称
	 */
	public String name() default "";
	/**
	 * 字段注释，用于编程理解
	 */
	public String label() default "";

	/**
	 * 字段长度,备用字段
	 */
	public int length() default 0;
	
	/**
	 * 版本
	 */
	public String version() default "1.0.0.0";
	/**
	 * 数据来源
	 * @return
	 */
	public String source() default "";

}