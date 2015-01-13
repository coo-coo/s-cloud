package com.coo.s.cloud;

import com.coo.s.cloud.bae.BaeFactory;
import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.cache.ext.XmemcachedRepository;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.NgbfMongoClient;
import com.kingstar.ngbf.s.mongo.NgbfMongoConfiguration;
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
	
	// TODO 从配置文件中获得
	public static String MC_ADDRESS = "10.253.46.24:11211 10.253.46.75:11211";
	
	// true:从部署中获得,false:从本地获得
	public static boolean CLOUD = false;

	private static INgbfMongoClient mongoClient;

	private static IRepository repository;

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
		if (mongoClient != null) {
			return mongoClient;
		} else {
			if (CLOUD) {
				mongoClient = BaeFactory.buildMongoClient();
			} else {
				NgbfMongoConfiguration config = NgbfMongoConfiguration.VOTE;
				mongoClient = new NgbfMongoClient(config);
			}
			return mongoClient;
		}
	}

	/**
	 * 获得Memcached客户端,Bae实现
	 */
	public static IRepository getMC() {
		if (repository != null) {
			return repository;
		} else {
			if (CLOUD) {
				repository = BaeFactory.buildMcRepository();
			} else {
				// 创建MemCachedRepository,需要建立Memcached服务
				repository = new XmemcachedRepository(MC_ADDRESS);
			}
			return repository;
		}
	}
}
