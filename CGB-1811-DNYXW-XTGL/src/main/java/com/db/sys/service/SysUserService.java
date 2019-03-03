package com.db.sys.service;



import java.util.Map;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysUser;
import com.db.sys.vo.SysUserDeptResult;

/**
 * 业务层接口对象(通过此对象的方法处理用户业务)
 * @author 000
 */

public interface SysUserService {
	/**
	 * 基于查询条件分页查询当前用户信息
	 * @param username 基于条件查询时的参数名
	 * @param pageCurrent 当前的页码值
	 * @return 当前页记录+分页信息
	 */
	PageObject<SysUser> findPageObjects(String username,Integer pageCurrent);
	/**
	 * 基于用户id,状态,修改用户更新数据
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById (Integer id,
			Integer valid,
			String modifiedUser);
	/**
	 * 根据用户信息和角色关联信息保存数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(SysUser entity, Integer[] roleIds);
	/**
	 * 根据用户id查询角色信息和用户信息存入map集合返回
	 * @param userId
	 * @return
	 */
	Map<String, Object> findObjectById(Integer userId);
	/**
	 * 根据修改的数据更新角色表和角色用户关系表中数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateObject(SysUser entity,Integer[] roleIds);
	int updatePassword(String password, String newPassword, String cfgPassword);
	int findObjectByColumn(String columnName,String columnValue);
}
