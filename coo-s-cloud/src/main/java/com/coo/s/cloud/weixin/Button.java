package com.coo.s.cloud.weixin;
/**
 * [公众服务号]按钮的基类 自定义菜单
 * @author boqing.shen
 * @since 1.0.0.0
 * @date 2013-07-25
 */
public class Button {

	private String name;

	private String type;

	private String key;

	private String url;

	public Button() {

	}

	public static Button click() {
		Button btn = new Button();
		btn.setType("click");
		return btn;
	}
	
	public static Button view() {
		Button btn = new Button();
		btn.setType("view");
		return btn;
	}
	
	public Button url(String url) {
		this.url = url;
		return this;
	}
	
	public Button key(String key) {
		this.key = key;
		return this;
	}
	
	public Button name(String name) {
		this.name = name;
		return this;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}