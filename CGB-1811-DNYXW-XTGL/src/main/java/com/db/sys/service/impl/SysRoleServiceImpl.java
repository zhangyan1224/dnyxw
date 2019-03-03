package com.db.sys.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.common.exception.ServiceException;
import com.db.common.util.PageUtils;
import com.db.common.vo.CheckBox;
import com.db.common.vo.PageObject;
import com.db.common.vo.SysRoleMenuVo;
import com.db.sys.dao.SysRoleDao;
import com.db.sys.dao.SysRoleMenuDao;
import com.db.sys.entity.SysRole;
import com.db.sys.service.SysRoleService;
/**
 * @Service注解与spring中的@controller的作用是等同的,只是一般用于描述对象不同
 * @author 000
 */
@Service//默认存储对象时为"类名,首字母小写"
public class SysRoleServiceImpl implements SysRoleService {
	/**
	 * @Autowired 注解用于告诉spring框架请按类型从spring容器中查找对象,然后赋值给属性
	 * (底层通过反射实现),这个过程称为DI
	 */
	@Autowired//DI (spring DI方式自动为属性赋值)
	@Qualifier("sysRoleDao")//可以基于此注解指定注入哪个实现类
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	@RequiresPermissions("sys:list")
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		//1.验证参数有效性
		  //1.1验证pageCurrent的合法性，
		  //不合法抛出IllegalArgumentException异常
		if (pageCurrent==null || pageCurrent<1) {//当前页码为空或者小于1
			throw new IllegalArgumentException("当前页码不正确");
		}
		//2.基于条件查询总记录数并进行相关判定
		  //2.1) 执行查询总记录数
		int rowCount = sysRoleDao.getRowCount(name);
		//2.2) 验证查询结果，假如结果为0不再执行如下操作,为0说明没查到
		if (rowCount==0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		//3.基于条件查询当前页记录(pageSize定义为3)
		//基于用户名和当前页码值查询当前记录
		//3.1)定义pageSize页面大小
		int pageSize=3;
		//3.2)计算startIndex开始下标
		int startIndex=(pageCurrent-1)*pageSize;//(当前页码-1)*页面大小
		//3.3)执行当前数据的查询操作
		List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
		//封装两次结果并返回
		//4.1)构建PageObject对象
		PageObject<SysRole> pageObject =PageUtils.newPageObject(rowCount, records, pageSize, pageCurrent);
		//5.返回封装结果。
		return pageObject;
	}

	@Override
	@RequiresPermissions("sys:list")
	public int saveObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
		if (entity==null) {
			throw new ServiceException("保存数据不能为空");
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("角色名不能为空");
		}
		if (menuIds==null ||menuIds.length==0) {
			throw new ServiceException("必须为角色指定权限!");
		}
		//2.保存角色自身数据
		int rows = sysRoleDao.insertObject(entity);
		//3.保存角色菜单关系数据
		sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		//4.返回结果
		return rows;
	}

	@Override
	@RequiresPermissions("sys:list")
	public int deleteObject(Integer id) {
		//1.验证参数的合法性
		if(id==null||id<1)
		throw new ServiceException("id的值不正确,id="+id);
		//2.执行dao操作
		int rows=sysRoleDao.deleteObject(id);
		if(rows==0)
		throw new ServiceException("数据可能已经不存在");
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		//3.返回结果
		return rows;

	}

	@Override
	@RequiresPermissions("sys:list")
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.验证参数的合法性
		if (id==null || id<1) {
			throw new IllegalArgumentException("id值无效");
		}
		//2.执行dao操作
		SysRoleMenuVo vo = sysRoleDao.findObjectById(id);
		if (vo==null) {
			throw new ServiceException("查询的数据可能不存在!");
		}
		//3.返回结果
		return vo;
	}

	@Override
	@RequiresPermissions("sys:list")
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
		if (entity==null) {
			throw new ServiceException("保存数据不能为空");
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("角色名不能为空");
		}
		if (menuIds==null ||menuIds.length==0) {
			throw new ServiceException("必须为角色指定权限!");
		}
		//2.更新角色自身数据
		int rows = sysRoleDao.updateObject(entity);
		if (rows==0) {
			throw new ServiceException("数据可能已经不存在!");
		}
		//3.1更新角色菜单关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		//3.2更新角色菜单关系数据
		sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		//4.返回结果
		return rows;
	}

	@Override
	@RequiresPermissions("sys:list")
	public List<CheckBox> findObjects() {
		List<CheckBox> list = sysRoleDao.findObjects();
		if (list==null||list.size()==0) {
			throw new ServiceException("没找到该用户所对应的角色");
		}
		return list;
	}
}
