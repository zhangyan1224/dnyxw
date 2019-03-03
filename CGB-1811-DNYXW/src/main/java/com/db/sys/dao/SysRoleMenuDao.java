package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 此dao对象对应sys_role_menus表中数据操作，此表中储存中数据时
 * 
 * @author 000
 *
 */
public interface SysRoleMenuDao {
	List<Integer> findMenuIdsByRoleIds(@Param("roleIds") Integer[] roleIds);

	int deleteObjectsByMenuId(Integer menuId);

	int insertObject(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);

	int deleteObjectsByRoleId(Integer roleId);

}
