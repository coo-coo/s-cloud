package com.coo.s.cloud.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coo.s.cloud.CloudFactory;
import com.coo.s.cloud.CooException;
import com.coo.s.cloud.model.Account;
import com.coo.s.cloud.model.Focus;
import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.MongoItem;
import com.kingstar.ngbf.s.mongo.QueryAttrs;
import com.kingstar.ngbf.s.ntp.NtpHead;
import com.kingstar.ngbf.s.ntp.NtpMessage;
import com.kingstar.ngbf.s.sms.Sms;
import com.kingstar.ngbf.s.util.StringUtil;

/**
 * 实现账号的注册、密码修改、账号登录等操作
 */
@Controller
@RequestMapping("/account")
public class AccountRest extends GenericRest {

	private Logger logger = Logger.getLogger(AccountRest.class);

	/**
	 * [用户自己调用]获得Profile的信息,通过手机号获取
	 */
	@RequestMapping(value = "/info/mobile/{mobile}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage infoByMobile(@PathVariable("mobile") String mobile) {
		QueryAttrs query = QueryAttrs.blank().add("mobile", mobile);
		// 查询获得列表，因为，数据较简单，直接从Mongo数据库中获得
		MongoItem mi = getMongo().findItemOne(Account.SET, query);
		return change(mi);
	}

	/**
	 * [用户调用] 获得Profile的信息,通过手机号获取
	 */
	@RequestMapping(value = "/info/uid/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage infoByUid(@PathVariable("uid") String uid) {
		// 获得对象
		MongoItem mi = getAccountMI(uid);
		return change(mi);
	}

	/**
	 * [Admin调用]账号状态变更，用于Admin管理 url?op=139xxxxxxxx
	 */
	@RequestMapping(value = "/update/uid/{uid}/status/{status}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage updateStatus(HttpServletRequest req,
			@PathVariable("uid") String uid,
			@PathVariable("status") String status) {
		// 获得对象
		MongoItem mi = getAccountMI(uid);
		// 更新数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("updater", this.getOperator(req));
		getMongo().update(Account.SET, mi.get_id(), map);
		return NtpMessage.ok();
	}

	/**
	 * 通过uid来获得MongoItem
	 */
	private MongoItem getAccountMI(String uid) {
		// TODO 从缓存中获取...
		QueryAttrs query = QueryAttrs.blank().add("uid", uid);
		return getMongo().findItemOne(Account.SET, query);
	}

	/**
	 * [用户调用]用户更新子集的属性信息,Map对象?
	 */
	@RequestMapping(value = "/update/param", method = RequestMethod.POST)
	@ResponseBody
	public NtpMessage updateParam(@RequestBody String data) {
		NtpMessage resp = NtpMessage.ok();
		// TODO Map对象?获得Android客户端传来的data(Topic)的数据
		NtpMessage sm = NtpMessage.bind(data);
		if (sm != null) {
			// 获得对象
			String uid = (String) sm.get("uid");
			MongoItem mi = getAccountMI(uid);
			// 字段名称 & 值,直接Map对象传递到数据库中
			String key = (String) sm.get("key");
			String value = (String) sm.get("value");
			Map<String, Object> item = new HashMap<String, Object>();
			item.put(key, value);
			getMongo().update(Account.SET, mi.get_id(), item);
		} else {
			resp = resp.head(NtpHead.PARAMETER_ERROR);
		}
		return resp;
	}

	/**
	 * [M端Task调用]获得Profile的列表信息信息,通过手机号获取,多个手机号以逗号隔开 用于MProfile的更新
	 * 
	 * @deprecated 暂不推荐....
	 */
	@RequestMapping(value = "/list/mobiles/{mobiles}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage listByMobiles(@PathVariable("mobiles") String mobiles) {
		String[] mobile = StringUtil.stringToArray(mobiles);
		// TODO 从缓存中获取.... 参见AccountJob...
		NtpMessage resp = NtpMessage.ok();
		for (String m : mobile) {
			String mcKey = "account." + m;
			MongoItem mi = (MongoItem) getMC().getValue(mcKey);
			if (mi != null) {
				// TODO 其它属性? 待处理....
				Account fb = new Account();
				fb.merge(mi);
				resp.add(fb);
			}
		}
		return resp;
	}

	/**
	 * [M端Task调用]获得全部账号列表信息，供Admin管理
	 */
	@RequestMapping(value = "/list/all", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage listByAll() {
		QueryAttrs query = QueryAttrs.blank().desc("_tsu");
		// 查询获得列表，因为，数据较简单，直接从Mongo数据库中获得
		List<MongoItem> items = getMongo().findItems(Account.SET, query);
		NtpMessage resp = NtpMessage.ok();
		for (MongoItem mi : items) {
			// TODO 其它属性? 待处理....
			Account fb = new Account();
			// Merge对象 先转换成对象，再传递
			// 不能用MongoItem.toMap()到M端,可能会产生Integer到Double的默认转换(GSON的问题)
			fb.merge(mi);
			resp.add(fb);
		}
		return resp;
	}

	/**
	 * [用户调用]先通过手机号获取验证码,检查手机号,向手机号发送[验证码]短信... TODO 客户端实现倒计时,120秒之后重新获取
	 */
	@RequestMapping(value = "/sms/mobile/{mobile}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage sms(@PathVariable("mobile") String mobile) {
		NtpMessage sm = NtpMessage.ok();
		try {
			if (isMobileExist(mobile)) {
				throw new CooException("该手机账号已注册!");
			}

			// 【注册】生成短信验证码，存储在MC中,(一个账号一天最多获取验证码3次?)

			// 生成验证码
			String sms = StringUtil.getRandomNumberCode(6);

			// 发送校验码
			sendSms(mobile, sms);

			// 存储验证码到MC中 默认存2分钟,M端控制2分钟内不能重发...
			// TODO 可能废短信很多?
			getMC().put(MC_PREFIX_SMS + mobile, sms,
					IRepository.MIN_1 * 2);

			logger.debug("短信校验码:" + mobile + "\t" + sms);

			sm.set("sms_code", sms);
		} catch (CooException e) {
			sm = NtpMessage.error(e.getMessage());
		}
		return sm;
	}

	/**
	 * 发送短信校验码，用于用户注册
	 */
	private void sendSms(String mobile, String smsCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("【COO】校验码:" + smsCode + ",您正在使用手机验证服务,感谢您的访问!");
		// 发送短信验证码
		Sms sms = Sms.init(mobile).content(sb.toString());
		CloudFactory.getSmsBuilder().send(sms);
	}

	/**
	 * [用户调用] 账号注册! 验证验证码的正确性
	 */
	@RequestMapping(value = "/register/mobile/{mobile}/sms/{sms}/password/{password}/source/{source}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage register(@PathVariable("mobile") String mobile,
			@PathVariable("sms") String sms,
			@PathVariable("password") String password,
			@PathVariable("source") String source) {
		NtpMessage sm = NtpMessage.ok();
		try {
			if (isMobileExist(mobile)) {
				throw new CooException("该手机账号已注册!");
			}

			// 注册第二步，验证验证码的正确性,从MC中取出SMS
			String value = (String) getMC().getValue(MC_PREFIX_SMS + mobile);
			boolean smsValid = (value != null && value.equals(sms)) ? true
					: false;

			if (!smsValid) {
				throw new CooException("验证码错误,请重新获得验证码!");
			}

			// 注册账号信息
			Account account = new Account(mobile, Account.TYPE_COMMON);
			// 设置ID和APP注册来源
			String uid = this.genUid();
			account.setUid(uid);
			account.setSource(source);
			// TODO password移动端加密?
			account.setPassword(password);
			
			// 插入...
			String _id = getMongo().insert(Account.SET, account.toMap());
			if (_id == null) {
				throw new CooException("注册失败");
			}
			// 返回，用于M端保存
			sm.set("uid", uid).set("type", Account.TYPE_COMMON);
		} catch (CooException e) {
			sm = NtpMessage.error(e.getMessage());
		}
		return sm;
	}

	/**
	 * [用户调用] 登陆，使用手机号|密码
	 */
	@RequestMapping(value = "/login/mobile/{mobile}/password/{password}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage login(@PathVariable("mobile") String mobile,
			@PathVariable("password") String password) {
		NtpMessage sm = NtpMessage.ok();
		try {
			// 登陆在mongoDB中查找
			QueryAttrs query = QueryAttrs.blank().add("mobile", mobile)
					.add("password", password);
			MongoItem mi = getMongo().findItemOne(Account.SET, query);

			if (mi == null) {
				throw new CooException("用户名或密码错误!");
			}
			sm.set("uid", (String) mi.get("uid")).set("type",
					Account.TYPE_COMMON);
		} catch (CooException e) {
			sm = NtpMessage.error(e.getMessage());
		}
		return sm;
	}

	/**
	 * [用户调用] 密码修改执行，M端绑定
	 */
	@RequestMapping(value = "/update/uid/{uid}/password/{password}", method = RequestMethod.GET)
	@ResponseBody
	public NtpMessage updatePassword(@PathVariable("uid") String uid,
			@PathVariable("password") String password) {
		// 获得对象
		MongoItem mi = getAccountMI(uid);
		// 更新对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		getMongo().update(Account.SET, mi.get_id(), map);
		return NtpMessage.ok();
	}

	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////

	/**
	 * MC:账号注册，手机验证码前缀 sms.mobile = 123456
	 */
	public static String MC_PREFIX_SMS = "sms.";

	public static String MC_PREFIX_MOBILE = "mobile.";

	/**
	 * 判断mobile是否存在
	 */
	public static boolean isMobileExist(String mobile) {
		QueryAttrs query = QueryAttrs.blank().add("mobile", mobile);
		MongoItem mi = getMongo().findItemOne(Account.SET, query);
		return mi == null ? true : false;
	}

	/**
	 * focus建立关联关系
	 */
	@SuppressWarnings("unused")
	private static NtpMessage focus(String account, String subject, String type) {
		// TODO 判断focus是否存在 暂不考虑Focus的重复性
		Focus focus = new Focus();
		focus.setAccount(account);
		focus.setSubject(subject);
		focus.setType(type);
		// 转化为Map对象
		getMongo().insert(Focus.C_NAME, focus.toMap());
		return NtpMessage.ok();
	}

	/**
	 * 取消focus关注关系
	 */
	@SuppressWarnings("unused")
	private static NtpMessage unfocus(String account, String subject,
			String type) {
		// 判断focus是否存在
		QueryAttrs query = QueryAttrs.blank().add("account", account)
				.add("subject", subject).add("type", type)
				.add("status", Focus.STATUS_VALID);
		MongoItem mi = getMongo().findItemOne(Focus.C_NAME, query);
		if (mi != null) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("status", Focus.STATUS_INVALID);
			getMongo().update(Focus.C_NAME, mi.get_id(), item);
		}
		// 取消 account对subject的关联关系
		return NtpMessage.ok();
	}

	/**
	 * 产生account的uid,参加业务数据的识别,传递采用uid进行传递
	 * 
	 * @return
	 */
	private String genUid() {
		return StringUtil.uuid();
	}

}
