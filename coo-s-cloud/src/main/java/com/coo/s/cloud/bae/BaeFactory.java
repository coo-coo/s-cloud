package com.coo.s.cloud.bae;

import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.NgbfMongoClient;

/**
 * 产生各种服务
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 */
public class BaeFactory {

	/**
	 * 获得BAE-Mongo服务
	 * 
	 * @since 1.0.0.0
	 */
	public static INgbfMongoClient buildMongoClient() {
		return new NgbfMongoClient(BaeConfig.MONGO);
	}

	/**
	 * 获得BAE-MC服务...
	 * 
	 * @since 1.0.0.0
	 */
	public static IRepository buildMcRepository() {
		return new BaeRepository();
	}
}
