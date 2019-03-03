package com.db.sys.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 此dao对象对应sys_user_roles表中数据操作
 * 此表中存储的是角色和菜单关系数据
 * @author 000
 *
 */
public interface SysUserRoleDao {
	/**
	 * 负责将用户与角色的关系数据写入到数据库
	 * @param userId 用户id
	 * @param roleIds 多个角色id
	 * @return
	 */
	int insertObject(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[]roleIds);
	/**
	 * 通过用户id查询所有的角色信息发送到页面
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);
	/**
	 * 根据userId删除数据
	 * @param userId
	 * @return
	 */
	int deleteObjectsByUserId(Integer userId);
}
