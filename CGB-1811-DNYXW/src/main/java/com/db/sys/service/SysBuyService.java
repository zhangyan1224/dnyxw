package com.db.sys.service;

import com.db.sys.entity.SysShangpin;

public interface SysBuyService {
	SysShangpin findObjectById(Integer id);
	int updateObject(Integer id);
}
