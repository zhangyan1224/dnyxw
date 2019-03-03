package com.db.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.common.exception.ServiceException;
import com.db.common.vo.Node;
import com.db.sys.dao.SysMenuDao;
import com.db.sys.dao.SysRoleMenuDao;
import com.db.sys.entity.SysMenu;
import com.db.sys.service.SysMenuService;

@Service
@RequiresPermissions("sys:list")
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	@RequiresPermissions("sys:list")
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> findObjects = sysMenuDao.findObjects();
		if(findObjects==null ||findObjects.size()==0){
			throw new ServiceException("没有找到对应菜单信息!");
		}
		return findObjects;
	}

	@Override
	@RequiresPermissions("sys:list")
	public int deleteObject(Integer id) {
		//1.验证数据的合法性
		if (id==null ||id<1) {
			throw new ServiceException("请先选择");
		}
		//2.基于id进行子元素查询
		int childCount = sysMenuDao.getChildCount(id);
		if (childCount>0) {
			throw new ServiceException("请先删除子菜单");
		}
		//3.删除菜单元素
		int rows = sysMenuDao.deleteObject(id);
		if (rows==0) {
			throw new ServiceException("此菜单可能已经不存在");
		}
		//4.删除角色,菜单关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//5.返回结果
		return rows;
	}

	@Override
	@RequiresPermissions("sys:list")
	public List<Node> findZtreeMenuNodes() {
		List<Node> list = sysMenuDao.findZtreeMenuNodes();
		if(list==null || list.size()<1)throw new ServiceException("没有找到菜单选项");
		return list;
	}

	@Override
	@RequiresPermissions("sys:list")
	public int saveObject(SysMenu entity) {
		//1.获取页面中数据，并对数据进行合法验证
		if (entity==null) {
			throw new IllegalArgumentException("保存的菜单不能为空");
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("菜单名不能为空!");
		}
		if (StringUtils.isEmpty(entity.getPermission())) {
			throw new ServiceException("权限标识不能为空!");
		}
		//2.将数据写入到数据库
		int rows;
		try {
			rows = sysMenuDao.insertObject(entity);
		} catch (Error e) {//error系统级错误
			e.printStackTrace();//给运维人员发短信
			throw new ServiceException("系统维护中,保存失败!");
		} catch (RuntimeException e) {
			e.printStackTrace();//给运维人员发短信
			throw new ServiceException("系统维护中,保存失败!");
		}
		//判定结果，并返回数据
		return rows;
	}

	@Override
	@RequiresPermissions("sys:list")
	public int updateObject(SysMenu entity) {
		//1.合法验证
		if(entity==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("菜单名不能为空");
		//2.更新数据
		int rows;
		try {
			rows = sysMenuDao.updateObject(entity);
		} catch (Error e) {//error系统级错误
			e.printStackTrace();//给运维人员发短信
			throw new ServiceException("系统维护中,保存失败!");
		} catch (RuntimeException e) {
			e.printStackTrace();//给运维人员发短信
			throw new ServiceException("系统维护中,保存失败!");
		}
		if (rows==0) {
			throw new ServiceException("记录可能已经不存在");
		}
		//3.返回数据
		return rows;
	}
	
}
