package com.db.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.db.common.annotation.RequiredCache;
import com.db.common.annotation.RequiredLog;
import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysUserDao;
import com.db.sys.dao.SysUserRoleDao;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserService;
import com.db.sys.vo.SysUserDeptResult;

/**
 * @Service注解与spring中的@controller的作用是等同的,只是一般用于描述对象不同
 * @author 000
 */
@Service // 默认存储对象时为"类名,首字母小写"
/**
 *@Transactional(rollbackFor=Throwable.class,timeout=60,isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
 *只要有异常就回滚,超时时间60秒,事物隔离级别不可以是脏读,事务的传播特性:如果没有事务创建新事务, 如果当前有事务参与当前事务
 */
@Transactional(rollbackFor=Throwable.class,timeout=60,isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class SysUserServiceImpl implements SysUserService {
	/**
	 * @Autowired 注解用于告诉spring框架请按类型从spring容器中查找对象,然后赋值给属性 (底层通过反射实现),这个过程称为DI
	 */
	@Autowired // DI (spring DI方式自动为属性赋值)
	@Qualifier("sysUserDao") // 可以基于此注解指定注入哪个实现类
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	@RequiredCache
	@Transactional(readOnly=true)//默认false,查询时加
	@RequiresPermissions("sys:list")
	public PageObject<SysUser> findPageObjects(String username, Integer pageCurrent) {
		// 1.验证参数有效性
		// 1.1验证pageCurrent的合法性，
		// 不合法抛出IllegalArgumentException异常
		if (pageCurrent == null || pageCurrent < 1) {// 当前页码为空或者小于1
			throw new IllegalArgumentException("当前页码不正确");
		}
		// 2.基于条件查询总记录数并进行相关判定
		// 2.1) 执行查询总记录数
		int rowCount = sysUserDao.getRowCount(username);
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作,为0说明没查到
		if (rowCount == 0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		// 3.基于条件查询当前页记录(pageSize定义为3)
		// 基于用户名和当前页码值查询当前记录
		// 3.1)定义pageSize页面大小
		int pageSize = 3;
		// 3.2)计算startIndex开始下标
		int startIndex = (pageCurrent - 1) * pageSize;// (当前页码-1)*页面大小
		// 3.3)执行当前数据的查询操作
		List<SysUser> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
		// 4.对分页信息以及当前页记录进行封装
		// 封装两次结果并返回
		// 4.1)构建PageObject对象
		PageObject<SysUser> pageObject = new PageObject<>();
		// 4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);// 当前页码值
		pageObject.setPageSize(pageSize);// 页面大小
		pageObject.setRowCount(rowCount);// 总行数
		pageObject.setRecords(records);// 当前页记录
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);// 总页数=(总行数-1)/页面大小的结果再+1
		// 5.返回封装结果。
		return pageObject;
	}

	@Override
	@RequiredLog("禁用启用")
	@RequiresPermissions("sys:user:valid")
	public int validById(Integer id, Integer valid, String modifiedUser) {
		// 1.合法性验证
		if (id == null || id <= 0)
			throw new ServiceException("参数不合法,id=" + id);
		if (valid != 1 && valid != 0)
			throw new ServiceException("参数不合法,valie=" + valid);
		if (StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("修改用户不能为空");
		// 2.执行禁用或启用操作
		int rows = 0;
		try {
			rows = sysUserDao.validById(id, valid, modifiedUser);
		} catch (Throwable e) {
			e.printStackTrace();
			// 报警,给维护人员发短信
			throw new ServiceException("底层正在维护");
		}
		// 3.判定结果,并返回
		if (rows == 0)
			throw new ServiceException("此记录可能已经不存在");
		return rows;

	}

	@Override
	@RequiredLog("添加用户")
	@RequiresPermissions("sys:list")
	public int saveObject(SysUser entity, Integer[] roleIds) {
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

		// 2.将数据写入数据库
		String salt = UUID.randomUUID().toString();
		entity.setSalt(salt);
		// 加密(先了解,讲shiro时再说)
		SimpleHash sHash = new SimpleHash("MD5", entity.getPassword(), salt);
		entity.setPassword(sHash.toHex());

		int rows = sysUserDao.insertObject(entity);
		int count = sysUserRoleDao.insertObject(entity.getId(), roleIds);// "1,2,3,4";
		// 3.返回结果
		return rows;
	}

	@Override
	@RequiresPermissions("sys:list")
	public Map<String, Object> findObjectById(Integer userId) {
		// 1.合法性验证
		if (userId == null || userId <= 0)
			throw new ServiceException("参数数据不合法,userId=" + userId);
		// 2.业务查询
		SysUser user = sysUserDao.findObjectById(userId);
		if (user == null)
			throw new ServiceException("此用户已经不存在");
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
		// 3.数据封装
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;

	}

	@Override
	@RequiresPermissions("sys:list")
	public int updateObject(SysUser entity, Integer[] roleIds) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(roleIds==null||roleIds.length==0)
			throw new IllegalArgumentException("必须为其指定角色");
		if (StringUtils.isEmpty(entity.getMoney()))
			throw new ServiceException("账户资金不能为空");
		//其它验证自己实现，例如用户名已经存在，密码长度，...
		//2.更新用户自身信息
		int rows=sysUserDao.updateObject(entity);
		if (rows==0) {
			throw new ServiceException("记录可能已经不存在");
		}
		//3.保存用户与角色关系数据
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObject(entity.getId(),roleIds);
		//4.返回结果
		return rows;
	}

	@Override
	@RequiresPermissions("sys:list")
	public int updatePassword(String password, String newPassword, String cfgPassword) {
		//1.对参数进行非空验证
		if (password == null || password=="")
			throw new ServiceException("原密码不能为空");
		if (newPassword == null || newPassword=="")
			throw new ServiceException("新密码不能为空");
		if (cfgPassword == null || cfgPassword=="")
			throw new ServiceException("确认密码不能为空");
		//2.验证密码的合法性
		//2.1验证两次输入的密码是否一致
		if (!newPassword.equals(cfgPassword)) {
			throw new IllegalArgumentException("两次输入的密码不一致");
		}
		//2.2判定输入的原密码是否正确
		SysUser user=(SysUser) SecurityUtils.getSubject().getPrincipal();//获取登录的用户信息
		//用现在输入的密码和原来的盐值加密形成新密码
		SimpleHash sh=new SimpleHash("MD5", password, user.getSalt(), 1);
		System.out.println(user.getPassword());
		System.out.println(sh.toHex());
		if (!user.getPassword().equals(sh.toHex())) {
			throw new IllegalArgumentException("输入的原密码不正确");
		}
		//2.3判定原密码是否与新密码一致
		if (newPassword.equals(password)) {
			throw new IllegalArgumentException("新密码不能与旧密码相同");
		}
		//3.更新密码
		String salt=UUID.randomUUID().toString();
		sh=new SimpleHash("MD5", newPassword, salt, 1);
		int rows = sysUserDao.updatePassword(sh.toHex(), //已加密的新密码
											salt, 
											user.getUsername());
		return rows;
	}
	@Override
	public int findObjectByColumn(String columnName,
			String columnValue) {
		if(StringUtils.isEmpty(columnName))
	    throw new IllegalArgumentException("参数名不能为空");
		if(StringUtils.isEmpty(columnValue))
		throw new IllegalArgumentException("参数值不能为空");
		int count=sysUserDao.findObjectByColumn(columnName, columnValue);
		if(count>0)
		throw new ServiceException(columnValue + "已存在");
		return count;
	}

}
