package com.db.sys.service;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysAccount;
import com.db.sys.entity.SysShangpin;

import java.util.List;


public interface SysAccountService {
	PageObject<SysShangpin> findPageObjects(String youxi_name, String username, String youxi_qufu, String shangpin_type, Integer pageCurrent, String youxi_zhanghao);
	int saveObject(SysAccount entity);
	SysAccount findObjectById(Integer id);
	int updateObject(SysAccount entity);

	int validById(Integer id, String shangpin_zhuangtai);
	List<SysShangpin> findGamenames();
	List<SysShangpin> findGamequfus(String youxi_name);
}
