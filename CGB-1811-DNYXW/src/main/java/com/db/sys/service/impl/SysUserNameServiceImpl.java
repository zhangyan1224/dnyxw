package com.db.sys.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.sys.dao.SysUserNamedao;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserNameService;
@Service
public class SysUserNameServiceImpl implements SysUserNameService{
@Autowired	
private SysUserNamedao sysUserNamedao;
@Override
public SysUser User(String username) {
	System.out.println(username);
	if(username==null||username=="") {
		throw new IllegalArgumentException("用户不存在");
	}
	SysUser user = sysUserNamedao.User(username);
	System.out.println(user);
	return user;  
	}
	
}
