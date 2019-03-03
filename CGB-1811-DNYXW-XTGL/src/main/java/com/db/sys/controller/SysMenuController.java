package com.db.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.sys.entity.SysMenu;
import com.db.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	/**
	 * view
	 * @return
	 */
	@RequestMapping("doMenuListUI")
	public String doMenuListUI() {
		return "sys/menu_list";
	}
	
	@RequestMapping("doMenuEditUI")
	public String doMenuEditUI(Integer id) {
		return "sys/menu_edit";
	}
	/**
	 * model
	 * @return
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(sysMenuService.findObjects());
	}//http://localhost/CGB-DB-SYS-V2.02/menu/doFindObjects.do
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		int rows = sysMenuService.deleteObject(id);
		return new JsonResult("delete OK"+rows);
	}//http://localhost/CGB-DB-SYS-V2.02/menu/doDeleteObject.do?id=80
	
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes(){
		return new JsonResult(sysMenuService.findZtreeMenuNodes());
	}//http://localhost/CGB-DB-SYS-V2.02/menu/doFindZtreeMenuNodes.do
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysMenu entity){
		int rows = sysMenuService.saveObject(entity);
		return new JsonResult("save ok"+rows);
	}//http://localhost/CGB-DB-SYS-V2.02/menu/saveObject.do
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysMenu entity){
		int rows = sysMenuService.updateObject(entity);
		return new JsonResult("update ok"+rows);
	}//http://localhost/CGB-DB-SYS-V2.02/menu/doUpdateObject.do
}
