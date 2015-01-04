package com.coo.s.cloud.bae;

import com.kingstar.ngbf.s.mongo.NgbfMongoConfiguration;

public class BaeConfig {

	/*** API KEY AND ACCESS KEY ***/
	public static String API_KEY = "O5G0O6oo7T9mAhqQqWEl5XCX";
	public static String SECRET_KEY = "Vsb5s2Q0xr28XsfnV5UTR93iqvEchETC";

	/*** Mongo相关配置 ***/
	// public static String MONGO_NAME = "yRmuEMINdCgscrHIKACJ";
	public static String MONGO_NAME = "HYmBXWukDQfadfjjQlOK";
	public static String MONGO_HOST = "mongo.duapp.com";
	public static int MONGO_PORT = 8908;

	/**
	 * BAE:Mongo数据库
	 */
	public static NgbfMongoConfiguration MONGO = new NgbfMongoConfiguration(
			MONGO_HOST, MONGO_PORT, MONGO_NAME, API_KEY, SECRET_KEY);

	// ///////////////////////////////////////////////////////

	/*** MySQL相关配置 ***/
	public static String MYSQLNAME = "eiZrxDYagZRYcJuTRljT";
	public static String MYSQLHOST = "sqld.duapp.com";
	public static String MYSQLPORT = "4050";

	/*** Redis相关配置 ***/
	public static String REDISNAME = "QTQTzJQhMKabjpSTIEX";
	public static String REDISHOST = "redis.duapp.com";
	public static String REDISPORT = "80";

	/*** Cache相关配置 ***/
	public static String CACHEID = "pPdkLzIvgBaHmqJvQSz";
	public static String CACHEHOST = "cache.duapp.com";
	public static String CACHEPORT = "20243";

	/*** Image配置 ***/
	public static String IMAGEHOST = "image.duapp.com";

	/*** BCS配置 ***/
	public static String BCSHOST = "bcs.duapp.com";
	public static String BUCKET = "xxxx";

}
