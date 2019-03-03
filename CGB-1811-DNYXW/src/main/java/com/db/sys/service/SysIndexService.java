package com.db.sys.service;

import com.db.sys.entity.SysShangpin;

import java.util.List;


public interface SysIndexService {

	List<SysShangpin> findObjectByName(String youxi_name);
}
