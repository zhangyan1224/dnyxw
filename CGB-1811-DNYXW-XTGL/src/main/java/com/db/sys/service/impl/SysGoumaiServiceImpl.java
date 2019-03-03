package com.db.sys.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysGoumaiDao;
import com.db.sys.entity.SysShangpin;
import com.db.sys.service.SysGoumaiService;
/**
 * @Service注解与spring中的@controller的作用是等同的,只是一般用于描述对象不同
 * @author 000
 */
@Service//默认存储对象时为"类名,首字母小写"
public class SysGoumaiServiceImpl implements SysGoumaiService {
	/**
	 * @Autowired 注解用于告诉spring框架请按类型从spring容器中查找对象,然后赋值给属性
	 * (底层通过反射实现),这个过程称为DI
	 */
	@Autowired
	@Qualifier("sysGoumaiDao")//可以基于此注解指定注入哪个实现类
	private SysGoumaiDao sysGoumaiDao;

	@Override
	@RequiresPermissions("sys:list")
	public PageObject<SysShangpin> findPageObjects(Integer pageCurrent) {
		//1.验证参数有效性
		  //1.1验证pageCurrent的合法性，
		  //不合法抛出IllegalArgumentException异常
		if (pageCurrent==null || pageCurrent<1) {//当前页码为空或者小于1
			throw new IllegalArgumentException("当前页码不正确");
		}
		//2.基于条件查询总记录数并进行相关判定
		  //2.1) 执行查询总记录数
		int rowCount = sysGoumaiDao.getRowCount();
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
		List<SysShangpin> records = sysGoumaiDao.findPageObjects(startIndex, pageSize);
		//4.对分页信息以及当前页记录进行封装
		//封装两次结果并返回
		//4.1)构建PageObject对象
		PageObject<SysShangpin> pageObject = new PageObject<>();
		//4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);//当前页码值
		pageObject.setPageSize(pageSize);//页面大小
		pageObject.setRowCount(rowCount);//总行数
		pageObject.setRecords(records);//当前页记录
		pageObject.setPageCount((rowCount-1)/pageSize+1);//总页数=(总行数-1)/页面大小的结果再+1
		//5.返回封装结果。
		return pageObject;
	}

	
	
	
	@Override
	
	@RequiresPermissions("sys:log:delete")
	public int deleteObjcts(Integer... ids) {
		//1.判定参数合法性
		if (ids==null || ids.length==0) {
			throw new IllegalArgumentException ("请选择一个");
		}
		//2.执行删除操作
		int rows;
		try {
			rows = sysGoumaiDao.deleteObjcts(ids);
		} catch (Throwable e) {
			//3.发出报警信息(例如给运维人员发短信)
			e.printStackTrace();
			throw new ServiceException("系统故障,正在恢复中...");
		}
		//4.对结果进行验证
		if (rows==0) {
			throw new ServiceException("记录可能不存在!");
		}
		//5.返回结果
		return rows;
	}

	@Override
	@RequiresPermissions("sys:list")
	public PageObject<SysShangpin> findPageObject(String goumai_name, Integer pageCurrent) {
		//1.验证参数有效性
		  //1.1验证pageCurrent的合法性，
		  //不合法抛出IllegalArgumentException异常
		if (pageCurrent==null || pageCurrent<1) {//当前页码为空或者小于1
			throw new IllegalArgumentException("当前页码不正确");
		}
		//2.基于条件查询总记录数并进行相关判定
		  //2.1) 执行查询总记录数
		int rowCount = sysGoumaiDao.getRowCount1(goumai_name);
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
		List<SysShangpin> records = sysGoumaiDao.findPageObject(goumai_name, startIndex, pageSize);
		//4.对分页信息以及当前页记录进行封装
		//封装两次结果并返回
		//4.1)构建PageObject对象
		PageObject<SysShangpin> pageObject = new PageObject<>();
		//4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);//当前页码值
		pageObject.setPageSize(pageSize);//页面大小
		pageObject.setRowCount(rowCount);//总行数
		pageObject.setRecords(records);//当前页记录
		pageObject.setPageCount((rowCount-1)/pageSize+1);//总页数=(总行数-1)/页面大小的结果再+1
		//5.返回封装结果。
		return pageObject;
	}

}
