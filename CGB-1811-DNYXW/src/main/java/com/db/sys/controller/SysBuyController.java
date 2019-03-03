package com.db.sys.controller;

import com.db.common.vo.JsonResult;
import com.db.sys.service.SysBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/buy/")
public class SysBuyController {
	@Autowired
	private SysBuyService sysBuyService;

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Integer id) {
		sysBuyService.updateObject(id);
		return new JsonResult("update ok");
	}


}
