package com.db.sys.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysGoumaiDao;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysShangpin;
import com.db.sys.service.SysGoumaiService;
import com.db.sys.service.SysLogService;
import com.db.sys.service.impl.SysGoumaiServiceImpl;

@Controller
@RequestMapping("/gm/")
/**
 * 返回日志页面
 * @author 000
 */
public class SysGoumaiController {
	@Autowired
	@Qualifier("sysGoumaiServiceImpl")
	private SysGoumaiService sysGoumaiService;
	/**
	 * @return 返回分页页面	View
	 */
	//先引入log_list.html,然后用数据替换
	@RequestMapping("doGoumaiListUI")
	public String doGoumaiListUI() {
		return "sys/goumai_list";
	}
	/**
	 * 返回数据	Model
	 * 查所有
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent) {
		PageObject<SysShangpin> PageObject = sysGoumaiService.findPageObjects(pageCurrent);
		return new JsonResult(PageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doFindPageObjects.do?pageCurrent=1&username=admin
	/**
	 * 查一个
	 * @param shangpin_zhuangtai
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObject")
	@ResponseBody
	public JsonResult doFindPageObject(String goumai_name,Integer pageCurrent) {
		PageObject<SysShangpin> PageObject = sysGoumaiService.findPageObject(goumai_name, pageCurrent);
		System.out.println(goumai_name);
		return new JsonResult(PageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doFindPageObjects.do?pageCurrent=1&username=admin
	
	@RequestMapping("deleteObjcts")
	@ResponseBody
	public JsonResult deleteObjcts(Integer... ids) {
		int deleteObjcts = sysGoumaiService.deleteObjcts(ids);
		return new JsonResult("delete ok"+deleteObjcts);
	}//http://localhost/CGB-DB-SYS-V2.02/log/deleteObjcts.do?ids=15,16
}
