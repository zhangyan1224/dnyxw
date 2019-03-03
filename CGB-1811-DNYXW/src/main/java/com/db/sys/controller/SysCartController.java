package com.db.sys.controller;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysCartDao;
import com.db.sys.entity.SysGoods;
import com.db.sys.entity.SysShangpin;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysCartService;
import com.db.sys.service.SysGoodsService;
import com.db.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ifsy
 * @create 2019年1月31日 下午2:29:21
 */
@Controller
@RequestMapping("/cart/")
public class SysCartController {

    @Autowired
    private SysCartService sysCartService;
    @Autowired
    private SysCartDao sysCartDao;

    @RequestMapping("doCartListUI")
    public String doCartListUI() {
        return "sys/goods_cart_list";
    }

    @RequestMapping("doFinObjectByShangpinIds")
	@ResponseBody
	public JsonResult doFinObjectByShangpinIds(String youxi_name,String youxi_qufu,
											   String shangpin_type, Integer pageCurrent) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		int user_id = user.getId();
		List<Integer> shangpinIds = sysCartDao.findShangpinIdsByUserId(user_id);
		Integer[] array={};
		PageObject<SysShangpin> findPageObjects = sysCartService.finObjectByShangpinIds(shangpinIds.toArray(array),user_id,youxi_name,youxi_qufu,shangpin_type,pageCurrent);
		System.out.println(findPageObjects);
		return new JsonResult(findPageObjects);
	}

    @RequestMapping("doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(Integer shangpin_id) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int user_id = user.getId();
        System.out.println("user_id:"+user_id+"+"+"shangpin_id"+shangpin_id);
        int i = sysCartService.deleteObject(user_id, shangpin_id);
        System.out.println("SysCartController.doDeleteObject.i:"+i);
        return new JsonResult("delete ok");
    }
    @Order(5)
    @RequestMapping("doTianjiaById")
    @ResponseBody
    public JsonResult doTianjiaById(Integer id) {
    	SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
    	int user_id = user.getId();
    	System.out.println("user_id:"+user_id+"+"+"id"+id);
    	int i = sysCartService.doTianjiaById(user_id, id);
    	System.out.println("SysCartController.doDeleteObject.i:"+i);
    	return new JsonResult("tianjia ok");
    }
    @RequestMapping("doFindGamenames")
	@ResponseBody
	public JsonResult doFindGamenames(){
		List<SysShangpin> gamenames = sysCartService.findGamenames();
		return new JsonResult(gamenames);
	}
	@RequestMapping("doFindGamequfus")
	@ResponseBody
	public JsonResult doFindGamequfus(String youxi_name){
		List<SysShangpin> gamenames = sysCartService.findGamequfus(youxi_name);
		return new JsonResult(gamenames);
	}
    

}
