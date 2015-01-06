package com.coo.s.cloud.bae;

import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.NgbfMongoClient;

/**
 * 产生各种服务
 * @author boqing.shen
 * @since 1.0.0.0
 */
public class BaeFactory {
	
	
	private static INgbfMongoClient mongoClient;
	
	private static IRepository repository;
	
	/**
	 * 获得BAE-Mongo服务
	 * @since 1.0.0.0
	 */
	public static INgbfMongoClient getMongo() {
		if(mongoClient==null){
			mongoClient = new NgbfMongoClient(BaeConfig.MONGO);
		}
		return mongoClient;
	}
	
	/**
	 * 获得BAE-MC服务...
	 * @since 1.0.0.0
	 */
	public static IRepository getMc() {
		if(repository==null){
			repository = new BaeRepository();
		}
		return repository;
	}
}
