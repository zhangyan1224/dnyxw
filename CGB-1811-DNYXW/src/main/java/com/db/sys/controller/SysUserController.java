package com.db.sys.controller;


import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserService;

@Controller
@RequestMapping("/user/")
/**
 * 返回日志页面
 * @author 000
 */
public class SysUserController {
	@Autowired
	@Qualifier("sysUserServiceImpl")
	private SysUserService sysUserService;
	/**
	 * @return 返回分页页面	View
	 */
	//先引入log_list.html,然后用数据替换
	@RequestMapping("doUserListUI")
	public String doUserListUI() {
		return "sys/user_list";
	}
	@RequestMapping("doUserEditUI")
	public String doUserEditUI(){
			return "sys/user_edit";
	}
	@RequestMapping("doUpdatePwdUI")
	public String doUpdatePwdUI(){
		return "sys/pwd_edit";
	}
	@RequestMapping("doZhuceUI")
	public String doZhuceUI(){
		return "sys/user_zhuce";
	}
	@RequestMapping("doUpdateMoneyUI")
	public String doUpdateMoneyUI(){
		return "sys/money_edit";
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
		PageObject<SysUser> PageObject = sysUserService.findPageObjects(username, pageCurrent);
		return new JsonResult(PageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doFindPageObjects.do?pageCurrent=1&username=admin
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id, Integer valid) {
		//获取修改者的用户名字信息
		SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
		int validById = sysUserService.validById(id, valid, user.getUsername());//"admin"用户将来是登陆用户
		return new JsonResult("update ok:"+validById);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doValidById.do?
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysUser entity,
			Integer[] roleIds){
		//获取修改者的用户名字信息
		System.out.println(roleIds);
		SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
		entity.setCreatedUser(user.getUsername());
		entity.setModifiedUser(user.getUsername());
		int saveObject = sysUserService.saveObject(entity,roleIds);
		return new JsonResult("save ok:"+saveObject);
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		Map<String,Object> map=
		sysUserService.findObjectById(id);
		return new JsonResult(map);
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
	    SysUser entity,Integer[] roleIds){
		//获取修改者的用户名字信息
				SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
				entity.setModifiedUser(user.getUsername());
		sysUserService.updateObject(entity,
				roleIds);
		return new JsonResult("update ok");
	}
	@RequestMapping("doUpdateMoney")
	@ResponseBody
	public JsonResult doUpdateMoney(Integer money){
		sysUserService.updateMoney(money);
		return new JsonResult("充值成功");
	}
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(
			String username,String password){
		//1.对用户信息进行封装
		Subject subject=SecurityUtils.getSubject();
		//2.提交用户信息(交给shiro框架进行认证)
		//2.1对用户进行封装
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);//身份信息,凭证信息
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		//2.2对用户信息进行身份认证
		subject.login(token);
		 //分析:
		   //1)token会传给shiro的SecurityManager
		   //2)SecurityManager将token传递给认证管理器
		   //3)认证管理器会将token传递给realm
		return new JsonResult("login ok");
	}
	@RequestMapping("doUpdatePassword")
	@ResponseBody
	public JsonResult doUpdatePassword(String password, String newPassword, String cfgPassword){
		sysUserService.updatePassword(password, newPassword, cfgPassword);
		return new JsonResult("update ok");
	}
	@RequestMapping("doFindObjectByColumn")
    @ResponseBody
    public JsonResult doFindObjectByColumn(
   		 String columnName,
   		 String columnValue){
   	 return new JsonResult(
   		sysUserService.findObjectByColumn(
   			columnName, columnValue));
    }
}