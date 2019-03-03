package com.db.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysZhuceService;

@Controller
@RequestMapping("/zhuce/")
/**
 * 返回日志页面
 * @author 000
 */
public class SysZhuceController {
	@Autowired
	@Qualifier("sysZhuceServiceImpl")
	private SysZhuceService sysZhuceService;
	
	@RequestMapping("doSaveObjects")
	@ResponseBody
	public JsonResult doSaveObjects(
			SysUser entity,
			Integer[] roleIds){
		System.out.println(entity);
		int saveObject = sysZhuceService.saveObjects(entity,roleIds);
		return new JsonResult("注册成功:"+saveObject);
	}
}