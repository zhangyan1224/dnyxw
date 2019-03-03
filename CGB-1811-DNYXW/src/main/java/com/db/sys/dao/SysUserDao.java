package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.entity.SysUser;

public interface SysUserDao {
	List<SysUser> findPageObjects(
			@Param("username") String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	int getRowCount(@Param("username") String username);
	/**
	 * 禁用和启用
	 * @param id 被修改的用户
	 * @param valid 状态
	 * @param modifiedUser 修改的用户
	 * @return
	 */
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	/**
	 * 负责将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
	/**
	 * 通过id查找数据呈现在页面
	 * @param id
	 * @return
	 */
	SysUser findObjectById(Integer id);
	/**
	 * 根据id修改角色数据
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
	/**
	 * 根据用户名查询用户信息封装到SysUser对象
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);
	/**
	 * 根据原始密码修改新密码
	 * @param newPassword
	 * @return
	 */
	int updatePassword(@Param("newPassword")String newPassword,
			@Param("salt")String salt,
			@Param("username")String username);
	
	int findObjectByColumn(
			@Param("columnName")String columnName,
			@Param("columnValue")String columnValue);


	int updateMoney(@Param("username")String username,
					@Param("money")Integer money);
}

