package com.coo.s.cloud;

import com.coo.s.cloud.bae.BaeFactory;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;

/**
 * 工厂类，获得各种服务句柄
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 * 
 */
public final class CloudFactory {
	
	/**
	 * 获得INgbfMongoClient句柄
	 * @return
	 */
	public static INgbfMongoClient getMongo() {
		// 返回Bae实现
		return BaeFactory.getMongo();
	}

}
