package com.db.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	/**
	 * @return 返回主页
	 */
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	/**
	 * @return 返回分页页面
	 */
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
			return "login";
	}
	
}//http://localhost/CGB-1811-DNYXW-XTGL/doIndexUI.do
