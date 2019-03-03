package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.common.vo.Node;

/**
 * 此dao对象对应sys_role_menus表中数据操作
 * 此表中存储的是角色和菜单关系数据
 * @author 000
 *
 */
public interface SysRoleMenuDao {
	/**
	 * 基于菜单id删除角色和菜单的关系数据
	 * @param menuId 菜单id
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);
	/**
	 * 基于菜单id和角色id执行添加操作
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObject(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	/**
	 * 基于roleid删除关联表中的数据
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	/**
	 * 基于角色id查询菜单id
	 * @param roleIds
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);
}
