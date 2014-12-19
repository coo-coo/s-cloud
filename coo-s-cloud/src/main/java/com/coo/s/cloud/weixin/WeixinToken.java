package com.coo.s.cloud.weixin;

/**
 * [公众服务号]微信通用接口凭证
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 */
public class WeixinToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}