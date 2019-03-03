package com.db.sys.dao;

import com.db.sys.entity.SysAccount;
import com.db.sys.entity.SysShangpin;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysIndexDao {

	List<SysShangpin> findObjectByName(@Param("youxi_name") String youxi_name);

	int getRowCount(@Param("youxi_name") String youxi_namei);

}
