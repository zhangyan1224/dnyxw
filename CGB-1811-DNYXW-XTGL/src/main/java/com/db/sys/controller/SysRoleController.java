package com.db.sys.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.CheckBox;
import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.common.vo.SysRoleMenuVo;
import com.db.sys.entity.SysRole;
import com.db.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
/**
 * 返回日志页面
 * @author 000
 */
public class SysRoleController {
	@Autowired
	@Qualifier("sysRoleServiceImpl")
	private SysRoleService sysRoleService;
	/**
	 * @return 返回分页页面	View
	 */
	//先引入role_list.html,然后用数据替换
	@RequestMapping("doRoleListUI")
	public String doRoleListUI() {
		return "sys/role_list";
	}
	
	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI(){
			return "sys/role_edit";
	}

	
	/**
	 * 返回数据	Model
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
		PageObject<SysRole> PageObject = sysRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(PageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/role/doFindPageObjects.do?pageCurrent=1&username=admin
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
	    		SysRole entity,Integer[] menuIds){
	    	int saveObject = sysRoleService.saveObject(entity,menuIds);
	return new JsonResult("save ok:"+saveObject);    
	}//http://localhost/CGB-DB-SYS-V2.02/role/doSaveObject.do
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
	 int rows = sysRoleService.deleteObject(id);
	return new JsonResult("delete Ok"+rows);
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
	    	SysRoleMenuVo findObjectById = sysRoleService.findObjectById(id);
	    	return new JsonResult(findObjectById);    
	}//http://localhost/CGB-DB-SYS-V2.02/role/doFindObjectById.do
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
	    		SysRole entity,Integer[] menuIds){
	    	int updateObject = sysRoleService.updateObject(entity, menuIds);
	return new JsonResult("save ok:"+updateObject); 
	}
	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindRoles(){
		List<CheckBox> list = sysRoleService.findObjects();
		return new JsonResult(list); 
	}
}
