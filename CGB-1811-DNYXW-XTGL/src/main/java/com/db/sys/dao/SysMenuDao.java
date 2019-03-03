package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.common.vo.Node;
import com.db.sys.entity.SysMenu;

public interface SysMenuDao {
	/**
	 * 查询所有
	 * @return
	 */
	List<Map<String, Object>> findObjects();
	/**
	 * 根据id查询是否有子菜单
	 * @param id
	 * @return
	 */
	int getChildCount(Integer id);
	/**
	 * 根据菜单id删除菜单的方法
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 借助dao对象,访问菜单节点信息
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	/**
	 * 在SysMenuDao中添加插入数据的方法，用于将菜单信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysMenu entity);
	/**
	 * 添加修改菜单方法，用于实现数据库中菜单信息的修改
	 * 更新菜单数据
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	/**
	 * 基于菜单id查询权限信息
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);
	
}
