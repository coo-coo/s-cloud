package com.coo.s.cloud.bae;

import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.NgbfMongoClient;

/**
 * 产生各种服务
 * @author boqing.shen
 * @since 1.0.0.0
 */
public class BaeFactory {
	
	
	private static INgbfMongoClient mongoClient;
	
	/**
	 * 获得BAE-Mongo服务
	 * @since 1.0.0.0
	 * @return
	 */
	public static INgbfMongoClient getMongo() {
		if(mongoClient==null){
			mongoClient = new NgbfMongoClient(BaeConfig.MONGO);
		}
		return mongoClient;
	}
}
