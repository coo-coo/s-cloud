package com.coo.s.cloud.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coo.s.cloud.CloudFactory;
import com.coo.s.cloud.bae.BaeFactory;
import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.MongoItem;
import com.kingstar.ngbf.s.mongo.QueryAttrs;
import com.kingstar.ngbf.s.ntp.NtpMessage;
import com.kingstar.ngbf.s.sms.Sms;
import com.kingstar.ngbf.s.util.StringUtil;

@Controller
@RequestMapping("/bae/demo")
public class BaeDemoRest {

	@RequestMapping(value = "/mongo/save", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage mongoSave() {
		INgbfMongoClient mongo = BaeFactory.getMongo();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("name", "SBQ");
		item.put("age", 36);
		item.put("ts", System.currentTimeMillis());
		String _id = mongo.insert("C_DEMO", item);
		return NtpMessage.ok().set("_id", _id);
	}

	@RequestMapping(value = "/mongo/find", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage mongoFind() {
		QueryAttrs query = QueryAttrs.blank().add("name", "SBQ");
		List<MongoItem> list = BaeFactory.getMongo().findItems("C_DEMO", query);
		NtpMessage sm = NtpMessage.ok().set("size", list.size());
		for (MongoItem mi : list) {
			sm.add(mi);
		}
		return sm;
	}

	@RequestMapping(value = "/mc/save/key/{key}/value/{value}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage mcSave(@PathVariable("key") String key,
			@PathVariable("value") String value) {
		IRepository mc = BaeFactory.getMc();
		mc.put(key, value, IRepository.MIN_1);
		return NtpMessage.ok().set(key, value)
				.set("ts", System.currentTimeMillis());
	}

	@RequestMapping(value = "/mc/get/key/{key}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage mcGet(@PathVariable("key") String key) {
		IRepository mc = BaeFactory.getMc();
		String value = (String) mc.getValue(key);
		return NtpMessage.ok().set(key, value)
				.set("ts", System.currentTimeMillis());
	}

	@RequestMapping(value = "/sms/mobile/{mobile}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage sms(@PathVariable("mobile") String mobile) {
		String smsCode = StringUtil.getRandomNumberCode(6);
		StringBuffer sb = new StringBuffer();
		sb.append("【COO】校验码测试:" + smsCode);
		// 发送短信验证码
		Sms sms = Sms.init(mobile).content(sb.toString());
		CloudFactory.getSmsBuilder().send(sms);
		return NtpMessage.ok().set("mobile", mobile).set("sms", smsCode);
	}

}
