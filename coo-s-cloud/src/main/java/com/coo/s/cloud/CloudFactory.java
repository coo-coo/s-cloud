package com.coo.s.cloud;

import com.coo.s.cloud.bae.BaeFactory;
import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.sms.SmsBuilder;
import com.kingstar.ngbf.s.sms.provider.ChuangMingSender;
import com.kingstar.ngbf.s.sms.provider.Log4jSmsCallback;

/**
 * 工厂类，获得各种服务句柄
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 * 
 */
public final class CloudFactory {

	public static String SMS_UID = "50099869";
	public static String SMS_MD5PWD = "b2e2b8cc1a90c3463ccab104e0450323";

	private static SmsBuilder smsBuilder;

	/**
	 * 获得SmsBuilder句柄,引用创明服务 参见:明网络[短信网]www.c123.com
	 * 
	 * @return
	 */
	public static SmsBuilder getSmsBuilder() {
		if (smsBuilder == null) {
			smsBuilder = SmsBuilder.init(
					new ChuangMingSender(SMS_UID, SMS_MD5PWD)).callback(
					new Log4jSmsCallback());
		}
		return smsBuilder;
	}

	/**
	 * 获得INgbfMongoClient句柄,Bae实现
	 * 
	 * @return
	 */
	public static INgbfMongoClient getMongo() {
		// TODO 通过配置获得
		return BaeFactory.getMongo();
	}

	/**
	 * 获得Memcached客户端,Bae实现
	 */
	public static IRepository getMC() {
		// TODO 通过配置获得
		return BaeFactory.getMc();
	}

}
