package com.db.sys.controller;

import com.db.common.vo.JsonResult;
import com.db.sys.entity.SysShangpin;
import com.db.sys.service.SysIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/index/")
public class SysIndexController {
	@Autowired
	private SysIndexService sysIndexService;

	@RequestMapping("doFindGoods")
	@ResponseBody
	public JsonResult doFindGoods(String youxi_name){
		List<SysShangpin> shangPins = sysIndexService.findObjectByName(youxi_name);
		return new JsonResult(shangPins);
	}

}
