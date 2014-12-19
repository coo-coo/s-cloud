package com.coo.s.cloud.weixin;

/**
 * [公众服务号]复杂按钮（父按钮） 包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 * @author boqing.shen
 * @since 1.0.0.0
 * @date 2013-07-25
 */
public class ComplexButton {

	private String name;

	public ComplexButton() {

	}

	public ComplexButton(String name) {
		this.name = name;
	}

	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

	public ComplexButton buttons(Button... sub_button) {
		this.sub_button = sub_button;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}