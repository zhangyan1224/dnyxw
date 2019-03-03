package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.entity.SysUser;


public interface SysUserNamedao {
	
	/**
	 * 基于用户名查找用户信息
	 * @param username
	 * @return
	 */
	SysUser User(@Param("username")String username);
}
