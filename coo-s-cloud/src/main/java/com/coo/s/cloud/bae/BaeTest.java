package com.coo.s.cloud.bae;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kingstar.ngbf.s.mongo.INgbfMongoClient;

public class BaeTest {
	
	private static Logger logger = Logger.getLogger(BaeTest.class);
	
	public static void main(String[] args) {
		INgbfMongoClient mongo = BaeFactory.getMongo();
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("name", "SBQ");
		item.put("age", 36);
		item.put("ts", System.currentTimeMillis());
		String _id = mongo.insert("C_DEMO", item);
		logger.debug("_id==" + _id);
	}
}
