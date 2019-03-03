package com.db.sys.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysLog;
import com.db.sys.service.SysLogService;

@Controller
@RequestMapping("/log/")
/**
 * 返回日志页面
 * @author 000
 */
public class SysLogController {
	@Autowired
	@Qualifier("sysLogServiceImpl")
	private SysLogService sysLogService;
	/**
	 * @return 返回分页页面	View
	 */
	//先引入log_list.html,然后用数据替换
	@RequestMapping("doLogListUI")
	public String doLogListUI() {
		return "sys/log_list";
	}
	/**
	 * 返回数据	Model
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		PageObject<SysLog> PageObject = sysLogService.findPageObjects(username, pageCurrent);
		return new JsonResult(PageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doFindPageObjects.do?pageCurrent=1&username=admin
	
	@RequestMapping("deleteObjcts")
	@ResponseBody
	public JsonResult deleteObjcts(Integer... ids) {
		int deleteObjcts = sysLogService.deleteObjcts(ids);
		return new JsonResult("delete ok"+deleteObjcts);
	}//http://localhost/CGB-DB-SYS-V2.02/log/deleteObjcts.do?ids=15,16
}
