package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {
	int deleteObjectsByUserId(Integer userId);

	List<Integer> findRoleIdsByUserId(Integer id);

	int insertObject(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);
}
