package com.coo.s.cloud.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coo.s.cloud.bae.BaeFactory;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.MongoItem;
import com.kingstar.ngbf.s.mongo.QueryAttrs;
import com.kingstar.ngbf.s.ntp.NtpMessage;

@Controller
@RequestMapping("/bae/mongo")
public class BaeMongoRest {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage hello() {
		NtpMessage sm = NtpMessage.ok().set("hello", "mongo");
		return sm;
	}

	@RequestMapping(value = "/demo/save", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage demoSave() {
		INgbfMongoClient mongo = BaeFactory.getMongo();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("name", "SBQ");
		item.put("age", 36);
		item.put("ts", System.currentTimeMillis());
		String _id = mongo.insert("C_DEMO", item);
		return NtpMessage.ok().set("_id", _id);
	}

	@RequestMapping(value = "/demo/find", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage demoFind() {
		QueryAttrs query = QueryAttrs.blank().add("name", "SBQ");
		List<MongoItem> list = BaeFactory.getMongo().findItems("C_DEMO", query);
		NtpMessage sm = NtpMessage.ok().set("size", list.size());
		for (MongoItem mi : list) {
			sm.add(mi);
		}
		return sm;
	}
}
