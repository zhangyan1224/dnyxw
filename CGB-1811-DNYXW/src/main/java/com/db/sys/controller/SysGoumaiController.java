package com.db.sys.controller;


import com.db.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysGoumaiDao;
import com.db.sys.entity.SysShangpin;
import com.db.sys.service.SysGoumaiService;
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
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String goumai_name = user.getUsername();
		PageObject<SysShangpin> PageObject = sysGoumaiService.findPageObjects(goumai_name,pageCurrent);
		return new JsonResult(PageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doFindPageObjects.do?pageCurrent=1&username=admin
	/**
	 * 查一个
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObject")
	@ResponseBody
	public JsonResult doFindPageObject(String youxi_name,Integer pageCurrent) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String goumai_name = user.getUsername();
		PageObject<SysShangpin> PageObject = sysGoumaiService.findPageObject(youxi_name,goumai_name,pageCurrent);
		System.out.println(youxi_name);
		return new JsonResult(PageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doFindPageObjects.do?pageCurrent=1&username=admin
	
	@RequestMapping("deleteObjcts")
	@ResponseBody
	public JsonResult deleteObjcts(Integer... ids) {
		int deleteObjcts = sysGoumaiService.deleteObjcts(ids);
		return new JsonResult("delete ok"+deleteObjcts);
	}//http://localhost/CGB-DB-SYS-V2.02/log/deleteObjcts.do?ids=15,16
}
