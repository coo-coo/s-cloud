package com.coo.s.cloud.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coo.s.cloud.CloudFactory;
import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.MongoItem;
import com.kingstar.ngbf.s.ntp.NtpHead;
import com.kingstar.ngbf.s.ntp.NtpMessage;

@Controller
@RequestMapping("/mock")
public class GenericCloudRest {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage hello() {
		NtpMessage sm = NtpMessage.ok().set("hello", "coo");
		return sm;
	}

	/**
	 * 将单个MI转换成为NtpMessage返回
	 */
	public static NtpMessage change(MongoItem mi) {
		NtpMessage resp = NtpMessage.ok();
		if (mi != null) {
			resp.set(mi.toMap());
		} else {
			resp = resp.head(NtpHead.NOT_FOUND);
		}
		return resp;
	}

	/**
	 * 获得操作者账号，即M端的Host，采用doGet获取 url?op=130xxxxxxxx
	 */
	protected String getOperator(HttpServletRequest req) {
		String operator = req.getParameter("op");
		if (operator == null) {
			operator = "UNKNOWN";
		}
		return operator;
	}

	// /////////////////////////////////////////////////////

	protected static INgbfMongoClient getMongo() {
		return CloudFactory.getMongo();
	}

	protected static IRepository getMC() {
		return CloudFactory.getMC();
	}

}
