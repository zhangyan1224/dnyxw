package com.db.sys.service;



import java.util.Map;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysUser;
import com.db.sys.vo.SysUserDeptResult;

/**
 * 业务层接口对象(通过此对象的方法处理用户业务)
 * @author 000
 */

public interface SysZhuceService {
	/**
	 * 根据用户信息和角色关联信息保存数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObjects(SysUser entity, Integer[] roleIds);
	/**
	 * 根据用户id查询角色信息和用户信息存入map集合返回
	 * @param userId
	 * @return
	 */
}
