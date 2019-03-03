package com.db.sys.controller;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysGoods;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysGoodsService;
import com.db.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 @author ifsy
 @create 2019年1月31日 下午2:29:21
*/
@Controller
@RequestMapping("/goods/")
public class SysGoodsController {

	@Autowired
	private SysGoodsService sysGoodsService;
	@Autowired
	private SysUserService sysUserService;
	

	@RequestMapping("doGoodsListLolmUI")
	public String doGoodsListLolmUI() {
		return "sys/goods_list_lolm";
	}
	@RequestMapping("doGoodsListLolrUI")
	public String doGoodsListLolrUI() {
		return "sys/goods_list_lolr";
	}

	@RequestMapping("doGoodsListDnfmUI")
	public String doLogList1UI() {
		return "sys/goods_list_dnfm";
	}
	@RequestMapping("doGoodsListDnfrUI")
	public String doGoodsListDnfrUI() {
		return "sys/goods_list_dnfr";
	}

	@RequestMapping("doGoodsListCsomUI")
	public String doGoodsListCsomUI() {
		return "sys/goods_list_csom";
	}

	@RequestMapping("doGoodsListCsorUI")
	public String doGoodsListCsorUI() {
		return "sys/goods_list_csor";
	}


	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String youxi_name,String youxi_qufu,String shangpin_type,String shangpin_zhuangtai,String youxi_zhanghao,Integer pageCurrent,Integer priceOrder) {
		System.out.println("Controller:"+youxi_name+":"+youxi_qufu+":"+shangpin_type+":"+shangpin_zhuangtai+":"+pageCurrent+":"+priceOrder);
		PageObject<SysGoods> findPageObjects = sysGoodsService.findPageObjects(youxi_name,youxi_qufu,shangpin_type,shangpin_zhuangtai,youxi_zhanghao,pageCurrent,priceOrder);
		System.out.println("Controller:sysGoodsService");
		System.out.println(findPageObjects);
		return new JsonResult(findPageObjects);
	}

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id,Integer valid) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		sysUserService.validById(id, valid, user.getUsername());
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Integer shangpin_id) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		int user_id = user.getId();
		sysGoodsService.saveObject(user_id,shangpin_id);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String,Object> map = sysUserService.findObjectById(id);
		return new JsonResult(map);
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysUser entity,Integer[] roleIds){
		sysUserService.updateObject(entity, roleIds);
		return new JsonResult("update ok");
	}
	@RequestMapping("doFindGamenames")
	@ResponseBody
	public JsonResult doFindGamenames(){
		List<SysGoods> gamenames = sysGoodsService.findGamenames();
		return new JsonResult(gamenames);
	}
	@RequestMapping("doFindGamequfus")
	@ResponseBody
	public JsonResult doFindGamequfus(String youxi_name){
		List<SysGoods> gamenames = sysGoodsService.findGamequfus(youxi_name);
		return new JsonResult(gamenames);
	}
}
