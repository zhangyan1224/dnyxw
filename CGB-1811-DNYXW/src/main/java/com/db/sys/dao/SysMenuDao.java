package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.common.vo.Node;
import com.db.sys.entity.SysMenu;

/*
 * 菜单管理业务层接口
 */
public interface SysMenuDao {
	List<String> findPermissions(@Param("menuIds") Integer[] menuIds);

	// 查询所有菜单以及本菜单对应上级菜单名字
	List<Map<String, Object>> findObjects();

	// 基于菜单id查询子菜单数
	int getChildCount(Integer id);

	// 基于菜单id删除菜单
	int deleteObject(Integer id);

	List<Node> findZtreeMenuNodes();

	int insertObject(SysMenu entity);

	int updateObject(SysMenu entity);
}
