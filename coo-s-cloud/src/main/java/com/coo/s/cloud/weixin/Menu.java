package com.coo.s.cloud.weixin;

import com.google.gson.Gson;

/**
 * [公众服务号]菜单对象的封装，菜单对象包含多个菜单项（最多只能有3个），这些菜单项即可以是子菜单项（不含二级菜单的一级菜单），也可以是父菜单项（包含二级菜单的菜单项）
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 * @date 2013-07-25
 */
public class Menu {

	public static void main(String[] args) {
		Button btn11 = Button.click().name("btn-11").key("K-11");
		Button btn12 = Button.click().name("btn-12").key("K-12");
		Button btn21 = Button.view().name("btn-21").url("http://ngbf-21");
		Button btn22 = Button.view().name("btn-22").url("http://ngbf-22");

		ComplexButton cb1 = new ComplexButton("M1").buttons(btn11, btn12);
		ComplexButton cb2 = new ComplexButton("M2").buttons(btn21, btn22);

		Menu menu = new Menu(cb1, cb2);
		String json = menu.toJson();
		System.out.println(json);
	}

	public static final String MENU_TYPE_CLICK_COMMON = "COMMON_CLICK";

	public static final String MENU_TYPE_VIEW_COMMON = "COMMON_VIEW";

	public static final String MENU_TYPE_COMPLEX = "COMPLEX";

	public Menu() {

	}

	public Menu(ComplexButton... buttons) {
		this.button = buttons;
	}

	private ComplexButton[] button;

	public ComplexButton[] getButton() {
		return button;
	}

	public void setButton(ComplexButton[] button) {
		this.button = button;
	}

	public void setButtons(ComplexButton... button) {
		this.button = button;
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}