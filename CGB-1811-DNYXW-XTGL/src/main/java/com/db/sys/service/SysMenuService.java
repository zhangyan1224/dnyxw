package com.db.sys.service;

import java.util.List;
import java.util.Map;

import com.db.common.vo.Node;
import com.db.sys.entity.SysMenu;
/**
 * @author 000
 *菜单管理的业务层接口
 */
public interface SysMenuService {
	/**
	 * 查询所有菜单的父类和本菜单的信息
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	/**
	 * 删除菜单id值指定的菜单以及菜单与角色的关系数据
	 * @param id 菜单id
	 * @return 删除的行数
	 */
	int deleteObject(Integer id);
	/**
	 * 借助dao对象,访问菜单节点信息,并返回.
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	/**
	 * 根据用户输入的信息对象对菜单执行添加操作
	 * @param entity
	 * @return
	 */
	int saveObject(SysMenu entity);
	/**
	 * 根据用户输入的信息对象对菜单执行修改操作
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
}
