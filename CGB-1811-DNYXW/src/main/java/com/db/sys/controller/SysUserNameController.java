package com.db.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserNameService;
import com.db.sys.service.SysZhuceService;
@Controller
@RequestMapping("/name/")
public class SysUserNameController {
	@Autowired
	private SysUserNameService sysUserNameService;
	protected String username;
	@RequestMapping("doUserName")
	@ResponseBody
	public JsonResult doUserName() {
		SysUser user1 = (SysUser)SecurityUtils.getSubject().getPrincipal();
		username =user1.getUsername();
		System.out.println(username);
		SysUser user = sysUserNameService.User(username);
		System.out.println(user);
		return new JsonResult(user);
		
	}
}
