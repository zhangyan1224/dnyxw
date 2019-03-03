package com.db.sys.service.impl;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysAccountDao;
import com.db.sys.dao.SysIndexDao;
import com.db.sys.entity.SysAccount;
import com.db.sys.entity.SysShangpin;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysAccountService;
import com.db.sys.service.SysIndexService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号业务
 * 
 * @author 000
 *
 */
@Service
public class SysIndexServiceImpl implements SysIndexService {
	@Autowired
	SysIndexDao sysIndexDao;

	@Override
	public List<SysShangpin> findObjectByName(String youxi_name) {
		int rowCount = sysIndexDao.getRowCount(youxi_name);
		if (rowCount==0) throw new ServiceException("该商品已不存在");
		List<SysShangpin> shangPins = sysIndexDao.findObjectByName(youxi_name);
		return shangPins;
	}

}
