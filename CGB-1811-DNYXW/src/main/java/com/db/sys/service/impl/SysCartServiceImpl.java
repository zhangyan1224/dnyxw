package com.db.sys.service.impl;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysCartDao;
import com.db.sys.dao.SysUserDao;
import com.db.sys.entity.SysGoods;
import com.db.sys.entity.SysShangpin;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysCartService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 @author ifsy
 @create 2019年1月31日 下午2:55:24
*/
@Transactional(rollbackFor = Throwable.class,timeout = 30,isolation = Isolation.READ_COMMITTED)
@Service
public class SysCartServiceImpl implements SysCartService {
	@Resource
	private SysUserDao sysUserDao;
	@Autowired
	private SysCartDao sysCartDao;

	@Override
	public List<Integer> findShangpinIdsByUserId() {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		int user_id = user.getId();
		List<Integer> shangpinIds = sysCartDao.findShangpinIdsByUserId(user_id);
		return shangpinIds;
	}
	@Override
	public PageObject<SysShangpin> finObjectByShangpinIds(Integer[] shangpin_ids,
														  Integer user_id,
														  String youxi_name,
														  String youxi_qufu,
														  String shangpin_type,
														  Integer pageCurrent) {
		//1.验证参数的有效性
		if(pageCurrent==null ||pageCurrent<1) throw new IllegalArgumentException("页码值不正确");
		//2.基于用户名查询总记录数并进行相关判定
		//List<Integer> shangpinIds = sysCartDao.findShangpinIdsByUserId(user_id);
		//int rowCount = shangpinIds.size();
		//System.out.println("rowCount"+rowCount);
		//3.基于用户名以及当前页码值查询当前的记录
		int pageSize=20;
		int startIndex=(pageCurrent-1)*pageSize;
		System.out.println("Impl层参数："+shangpin_ids+":"+youxi_name+":"+youxi_qufu+":"+shangpin_type+":"+startIndex+":"+pageSize);
		List<SysShangpin> records = sysCartDao.finObjectByShangpinIds(shangpin_ids,youxi_name,youxi_qufu,shangpin_type,startIndex,pageSize);
		int rowCount = records.size();
		System.out.println("Impl层："+records);
		if (rowCount==0) throw new ServiceException("没有找到对应记录");
		//4.封装两次结果并返回
		PageObject<SysShangpin> po = new PageObject<>();
		po.setRowCount(rowCount);
		po.setRecords(records);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		po.setPageCount((rowCount-1)/pageSize+1);
		return po;
	}

	@Override
	public int deleteObject(Integer user_id,Integer shangpin_id) {
		int rows = sysCartDao.deleteObject(user_id,shangpin_id);
		if(rows==0) throw new ServiceException("记录可能不存在");
		System.out.println("SysCartServiceImpl.deleteObject.rows:"+rows);
		return rows;
	}
	@Override
	public int doTianjiaById(Integer user_id, Integer id) {
		int rows = sysCartDao.doTianjiaById(user_id,id);
		if(rows==0) throw new ServiceException("记录可能不存在");
		System.out.println("SysCartServiceImpl.doTianjiaById.rows:"+rows);
		return rows;
	}
	@Override
	public List<SysShangpin> findGamenames() {
		List<SysShangpin> gamenames = sysCartDao.findGamenames();
		return gamenames;
	}
	@Override
	public List<SysShangpin> findGamequfus(String youxi_name) {
		List<SysShangpin> gamequfus = sysCartDao.findGamequfus(youxi_name);
		return gamequfus;
	}

}
