package com.db.sys.service;



import java.util.List;

import com.db.common.vo.CheckBox;
import com.db.common.vo.PageObject;
import com.db.common.vo.SysRoleMenuVo;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysRole;

/**
 * 业务层接口对象(通过此对象的方法处理日志业务)
 * @author 000
 */

public interface SysRoleService {
	/**
	 * 基于查询条件分页查询当前日志信息
	 * @param name 基于条件查询时的参数名
	 * @param pageCurrent 当前的页码值
	 * @return 当前页记录+分页信息
	 */
	PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
	/**
	 * 基于SysRole对象和用户角色id中的数据执行添加操作
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int saveObject (SysRole entity,Integer[] menuIds);
	/**
	 * 基于角色id删除数据
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 根据角色id查询角色信息和关联菜单id,返回SysRoleMenuVo对象
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	/**
	 * 基于SysRole对象和用户角色id中的数据执行修改操作
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int updateObject(SysRole entity,Integer[] menuIds);
	/**
	 * 查询角色id和名字
	 * @return
	 */
	List<CheckBox> findObjects();
}
