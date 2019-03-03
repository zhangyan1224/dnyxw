package com.db.sys.service.impl;

import com.db.common.exception.ServiceException;
import com.db.sys.dao.SysBuyDao;
import com.db.sys.entity.SysShangpin;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysBuyService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysBuyServiceImpl implements SysBuyService {
	@Autowired
	SysBuyDao sysBuyDao;

	@Override
	public SysShangpin findObjectById(Integer id) {
		SysShangpin record = sysBuyDao.findObjectById(id);
		return record;
	}
	@Transactional(timeout=30,rollbackFor=RuntimeException.class)
	@Override
	public int updateObject(Integer id) {
		System.out.println("SysBuyServiceImpl.updateObject:id:"+id);
		SysShangpin entity = sysBuyDao.findObjectById(id);
		System.out.println("SysBuyServiceImpl.updateObject:entity:"+entity);
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String username = user.getUsername();
		System.out.println("SysBuyServiceImpl.updateObject:username:"+username);
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		entity.setGoumai_name(username);
		entity.setShangpin_zhuangtai("已购买");
		Integer price = entity.getShangpin_jiage();
		Integer maiJiaMoney = user.getMoney()-price;
		if (maiJiaMoney<0){
			throw new ServiceException("余额不足，请充值");
		}
		String shangjia_name = entity.getShangjia_name();
		System.out.println("SysBuyServiceImpl.updateObject:shangjia_name:"+shangjia_name);
		SysUser shangjiaUser = sysBuyDao.findObjectByUserName(shangjia_name);
		Integer shangjiaMoner = shangjiaUser.getMoney()+price;
		if (shangjia_name.equals(username)) {
			throw new ServiceException("不可以购买自己上架的商品");
		}
		int row;
		try {
			row = sysBuyDao.updateObject(entity);
			sysBuyDao.updateUserMoneyByUserName(username,maiJiaMoney);
			sysBuyDao.updateUserMoneyByUserName(shangjia_name,shangjiaMoner);
		} catch (Exception e){
			e.printStackTrace();
			throw new ServiceException("更新失败");
		}
		return row;
	}
}
