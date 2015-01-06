package com.coo.s.cloud.weixin;

import com.ericxu131.exwechat.model.event.ClickEvent;
import com.ericxu131.exwechat.model.message.Message;
import com.ericxu131.exwechat.model.message.NewsMessage;
import com.ericxu131.exwechat.model.message.TextMessage;
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
	protected Message onMessage(Message msg) {
		if (msg instanceof ClickEvent) {
			// 获得自定义菜单的单击事件
			ClickEvent ce = (ClickEvent) msg;
			return onHandle(ce);
		}

		if (msg instanceof TextMessage) {
			// 获得消息的输入文本
			TextMessage tm = (TextMessage) msg;
			return onHandle(tm);
		}
		return null;
	}

	/**
	 * 当消息的输入文本
	 * 
	 * @since 1.0.0.0
	 */
	protected TextMessage onHandle(TextMessage tm) {
		return null;
	}

	/**
	 * 当自定义菜单的单击事件
	 * 
	 * @since 1.0.0.0
	 */
	protected TextMessage onHandle(ClickEvent ce) {
		return null;
	}

	/**
	 * 回复TextMessage
	 * 
	 * @since 1.0.0.0
	 */
	protected TextMessage replyText(Message msg) {
		TextMessage tm = new TextMessage();
		tm.setFromUserName(msg.getToUserName());
		tm.setToUserName(msg.getFromUserName());
		return tm;
	}

	/**
	 * 回复NewsMessage
	 * 
	 * @since 1.0.0.0
	 */
	protected NewsMessage replyNews(Message msg) {
		NewsMessage nm = new NewsMessage();
		nm.setFromUserName(msg.getToUserName());
		nm.setToUserName(msg.getFromUserName());
		return nm;
	}
}
