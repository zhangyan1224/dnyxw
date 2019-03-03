package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.entity.SysUser;

public interface SysZhuceDao {
	/**
	 * 负责将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
	int findObjectByColumn(
			@Param("columnName")String columnName,
			@Param("columnValue")String columnValue);

}
