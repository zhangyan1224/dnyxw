package com.db.sys.service.impl;

import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.common.exception.ServiceException;
import com.db.sys.dao.SysUserRoleDao;
import com.db.sys.dao.SysZhuceDao;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysZhuceService;

/**
 * @Service注解与spring中的@controller的作用是等同的,只是一般用于描述对象不同
 * @author 000
 */
@Service // 默认存储对象时为"类名,首字母小写"
/**
 *@Transactional(rollbackFor=Throwable.class,timeout=60,isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
 *只要有异常就回滚,超时时间60秒,事物隔离级别不可以是脏读,事务的传播特性:如果没有事务创建新事务, 如果当前有事务参与当前事务
 */
public class SysZhuceServiceImpl implements SysZhuceService {
	/**
	 * @Autowired 注解用于告诉spring框架请按类型从spring容器中查找对象,然后赋值给属性 (底层通过反射实现),这个过程称为DI
	 */
	@Autowired // DI (spring DI方式自动为属性赋值)
	@Qualifier("sysZhuceDao") // 可以基于此注解指定注入哪个实现类
	private SysZhuceDao sysZhuceDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;


	@Override
	public int saveObjects(SysUser entity, Integer[] roleIds) {
		System.out.println(entity);
		// 1.验证数据合法性
		if (entity == null)
			throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getUsername()))
			throw new ServiceException("用户名不能为空");
		if (StringUtils.isEmpty(entity.getPassword()))
			throw new ServiceException("密码不能为空");
		if (StringUtils.isEmpty(entity.getMoney()))
			throw new ServiceException("账户资金不能为空");
		if (roleIds == null || roleIds.length == 0)
			throw new ServiceException("至少要为用户分配角色");
		int count1 = sysZhuceDao.findObjectByColumn("username", entity.getUsername());
		if(count1>0)
			throw new ServiceException(entity.getUsername() + "已存在");

		// 2.将数据写入数据库
		String salt = UUID.randomUUID().toString();
		entity.setSalt(salt);
		// 加密(先了解,讲shiro时再说)
		SimpleHash sHash = new SimpleHash("MD5", entity.getPassword(), salt);
		entity.setPassword(sHash.toHex());

		int rows = sysZhuceDao.insertObject(entity);
		int count = sysUserRoleDao.insertObject(entity.getId(), roleIds);// "1,2,3,4";
		// 3.返回结果
		return rows;
	}



}
