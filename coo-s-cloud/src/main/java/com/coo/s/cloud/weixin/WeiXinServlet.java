package com.coo.s.cloud.weixin;

import com.ericxu131.exwechat.model.message.Message;
import com.ericxu131.exwechat.web.WechatServlet;

/**
 * [公众服务号]核心servlet 交由子类集成
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 * 
 */
public abstract class WeiXinServlet extends WechatServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5380556977710319617L;

	@Override
	protected String getToken() {
		// 验证Token,参见SignUtil中的validToken
		return "validateToken";
	}

	@Override
	protected Message onMessage(Message message) {
		// 调用核心业务类
		// Message resMsg = CoreService.processRequest(message);
		return null;
	}
}
