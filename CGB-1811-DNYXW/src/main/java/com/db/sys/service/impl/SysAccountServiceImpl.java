package com.db.sys.service.impl;

import java.util.List;

import com.db.sys.entity.SysShangpin;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.common.exception.ServiceException;
import com.db.common.util.PageUtils;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysAccountDao;
import com.db.sys.entity.SysAccount;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysAccountService;

/**
 * 账号业务
 * 
 * @author 000
 *
 */
@Service
public class SysAccountServiceImpl implements SysAccountService {
	@Autowired
	SysAccountDao sysAccountDao;

	// 查询商品信息
	@Override
	public PageObject<SysShangpin> findPageObjects(String youxi_name,String username,String youxi_qufu,String shangpin_type,Integer pageCurrent,String youxi_zhanghao) {
		//1.验证参数有效性
		//1.1验证pageCurrent的合法性，
		//不合法抛出IllegalArgumentException异常
		if (pageCurrent==null || pageCurrent<1) {//当前页码为空或者小于1
			throw new IllegalArgumentException("当前页码不正确");
		}
		//2.基于条件查询总记录数并进行相关判定
		//2.1) 执行查询总记录数
		System.out.println("SysShangjiaServiceImpl:findPageObjects:参数:"+youxi_name+":"+username+":"+youxi_qufu+":"+shangpin_type+":"+pageCurrent);
		System.out.println("username:"+username);
		int rowCount = sysAccountDao.getRowCount1(youxi_name,username,youxi_qufu,shangpin_type,youxi_zhanghao);
		System.out.println("Imple:findPageObjects:rowCount:"+rowCount);
		//2.2) 验证查询结果，假如结果为0不再执行如下操作,为0说明没查到
		if (rowCount==0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		//3.基于条件查询当前页记录(pageSize定义为3)
		//基于用户名和当前页码值查询当前记录
		//3.1)定义pageSize页面大小
		int pageSize=20;
		//3.2)计算startIndex开始下标
		int startIndex=(pageCurrent-1)*pageSize;//(当前页码-1)*页面大小
		//3.3)执行当前数据的查询操作
		List<SysShangpin> records = sysAccountDao.findPageObjects(youxi_name,username,youxi_qufu,shangpin_type,startIndex,pageSize,youxi_zhanghao);
		//4.对分页信息以及当前页记录进行封装
		//封装两次结果并返回
		//4.1)构建PageObject对象
		System.out.println("SysShangjiaServiceImpl:findPageObjects:records:"+records);
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

	// 实现商品上架
	@Override
	public int saveObject(SysAccount entity) {
		// 1.验证数据合法性
		if (entity == null)
			throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getYouxi_zhanghao()))
			throw new ServiceException("游戏账号不能为空");
		if (StringUtils.isEmpty(entity.getYouxi_mima()))
			throw new ServiceException("游戏密码不能为空");
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String shangjia_name = user.getUsername();
		entity.setShangjia_name(shangjia_name);
		entity.setShangpin_zhuangtai("已上架");
		System.out.println(entity.toString());
		int rows = sysAccountDao.insertObject(entity);
		System.out.println(rows);
		return rows;
	}

	@Override
	public SysAccount findObjectById(Integer id) {
		SysAccount record = sysAccountDao.findObjectById(id);
		return record;
	}
	@Override
	public int updateObject(SysAccount entity) {
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		int row = sysAccountDao.updateObject(entity);
		return row;
	}

	@Override
	public int validById(Integer id, String shangpin_zhuangtai) {
		if (id == null || id <= 0)
			throw new ServiceException("参数不合法");
		int row = sysAccountDao.validById(id,shangpin_zhuangtai);
		return row;
	}

	@Override
	public List<SysShangpin> findGamenames() {
		List<SysShangpin> gamenames = sysAccountDao.findGamenames();
		return gamenames;
	}

	@Override
	public List<SysShangpin> findGamequfus(String youxi_name) {
		List<SysShangpin> gamequfus = sysAccountDao.findGamequfus(youxi_name);
		return gamequfus;
	}
}
