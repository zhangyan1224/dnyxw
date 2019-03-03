package com.db.sys.controller;

import com.db.sys.entity.SysShangpin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysAccount;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysAccountService;

import java.util.List;

@Controller
@RequestMapping("/account/")
public class SysAccountController {
	@Autowired
	private SysAccountService sysAccountService;

	// 联盟账号
	@RequestMapping("doYXLMAccountListUI")
	public String doYXLMAccountListUI() {
		return "sys/yxlmaccount_list";
	}

	// 反恐账号
	@RequestMapping("doFKJYAccountListUI")
	public String doFKJYAccountListUI() {
		return "sys/fkjyaccount_list";
	}

	// 地下城账号
	@RequestMapping("doDXCAccountListUI")
	public String doDXCAccountListUI() {
		return "sys/dxcaccount_list";
	}

	// 联盟金币
	@RequestMapping("doYXLMGoldListUI")
	public String doYXLMGoldListUI() {
		return "sys/yxlmgold_list";
	}

	// 反恐金币
	@RequestMapping("doFKJYGoldListUI")
	public String doFKJYGoldListUI() {
		return "sys/fkjygold_list";
	}

	// 地下城金币
	@RequestMapping("doDXCGoldListUI")
	public String doAccountListUI() {
		return "sys/dxcgold_list";
	}

	// 编辑页面
	@RequestMapping("doAccountEditUI")
	public String doAccountEditUI() {
		return "sys/account_edit";
	}

	@RequestMapping("doShangjiaUI")
	public String doShangjiaUI() {
		return "shangjia_edit";
	}

	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password) {

		// 1.获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		// 2.通过Subject提交用户信息,交给shiro框架进行认证操作
		// 2.1对用户进行封装
		UsernamePasswordToken token = new UsernamePasswordToken(username, // 身份信息
				password);// 凭证信息
		// 2.2对用户信息进行身份认证
		subject.login(token);
		// 分析:
		// 1)token会传给shiro的SecurityManager
		// 2)SecurityManager将token传递给认证管理器
		// 3)认证管理器会将token传递给realm
		return new JsonResult("login ok");
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String youxi_name,String youxi_qufu,String shangpin_type,Integer pageCurrent,String youxi_zhanghao) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String username = user.getUsername();
		PageObject<SysShangpin> pageObject = sysAccountService.findPageObjects(youxi_name,username,youxi_qufu,shangpin_type,pageCurrent,youxi_zhanghao);
		System.out.println(pageObject);
		return new JsonResult(pageObject);
	}//http://localhost/CGB-DB-SYS-V2.02/log/doFindPageObjects.do?pageCurrent=1&username=admin

	@ResponseBody
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysAccount entity) {
		sysAccountService.saveObject(entity);
		return new JsonResult("save ok");
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		SysAccount record = sysAccountService.findObjectById(id);
		System.out.println(record.toString());
		return new JsonResult(record);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysAccount entity) {
		System.out.println("修改" + entity.toString());
		sysAccountService.updateObject(entity);
		return new JsonResult("update ok");
	}

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id, String shangpin_zhuangtai) {
		sysAccountService.validById(id, shangpin_zhuangtai);
		return new JsonResult("update ok");
	}

	@RequestMapping("doFindGamenames")
	@ResponseBody
	public JsonResult doFindGamenames(){
		List<SysShangpin> gamenames = sysAccountService.findGamenames();
		return new JsonResult(gamenames);
	}
	@RequestMapping("doFindGamequfus")
	@ResponseBody
	public JsonResult doFindGamequfus(String youxi_name){
		List<SysShangpin> gamenames = sysAccountService.findGamequfus(youxi_name);
		return new JsonResult(gamenames);
	}


}
