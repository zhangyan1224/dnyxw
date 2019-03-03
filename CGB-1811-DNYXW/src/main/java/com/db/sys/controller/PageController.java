package com.db.sys.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PageController {
	/**
	 * 返回主页面
	 * 
	 * @return
	 */
	//主页
	//http://localhost/CGB-1811-DNYXW/doIndexUI.do
	//买家
	//http://localhost/CGB-1811-DNYXW/doIndexUIM.do
	//卖家
	//http://localhost/CGB-1811-DNYXW/doIndexUIS.do
	//购物车
	//http://localhost/CGB-1811-DNYXW/doCartUI.do
	//上架
	//http://localhost/CGB-1811-DNYXW/doShangjiaUI.do
	//个人中心商家
	//http://localhost/CGB-1811-DNYXW/doGerenUIS.do
	//个人中心买家
	//http://localhost/CGB-1811-DNYXW/doGerenUIM.do
	@RequestMapping("doDetail")
	public String doDetail() {
		return "detail";
	}
	@RequestMapping("doIndexUI")
	  public String doIndexUI(){
		  return "index";//WEB-INF/pages/starter.html
	  }
	//商家
	@RequestMapping("doIndexUIS")
	public String doIndexUI1() {
		return "guanli";
	}
	
	@RequestMapping("doShangjiaUI")
	public String doShangjiaUI() {
		return "shangjia";
	}
	@RequestMapping("doGerenUIS")
	public String doGerenUIS() {
		return "starterS";
	}
	@RequestMapping("doGerenUIM")
	public String doGerenUIM() {
		return "starterM";
	}
	//买家
	@RequestMapping("doIndexUIM")
	public String doIndexUIM() {
		return "shop";
	}
	@Order(1)
	@RequestMapping("doCartUI")
	public String doCartUI() {
		return "cart";
	}
	

	/**
	 * 返回分页页面
	 * 
	 * @return
	 */
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}

	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
}
