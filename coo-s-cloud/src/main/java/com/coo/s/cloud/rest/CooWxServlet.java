package com.coo.s.cloud.rest;

import com.coo.s.cloud.weixin.WeiXinServlet;
import com.ericxu131.exwechat.model.event.ClickEvent;
import com.ericxu131.exwechat.model.message.TextMessage;

/**
 * COO 微信Servlet,用于微信公众服务号支持
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 * 
 */
public class CooWxServlet extends WeiXinServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1600593100095783329L;

	@Override
	protected String getToken() {
		return "COO";
	}

	@Override
	protected TextMessage onHandle(TextMessage msg) {
		TextMessage resp = this.replyText(msg);
		resp.setContent("你输入的内容是:" + msg.getContent());
		return resp;
	}

	@Override
	protected TextMessage onHandle(ClickEvent msg) {
		TextMessage resp = this.replyText(msg);
		resp.setContent("你点击的菜单键是:" + msg.getEventKey());
		return resp;
	}
}
