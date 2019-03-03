package com.db.sys.dao;

import com.db.sys.entity.SysShangpin;
import com.db.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysBuyDao {

	SysShangpin findObjectById(@Param("id")Integer id);

	int updateObject(SysShangpin entity);

	int updateUserMoneyByUserName(@Param("username")String username,
								  @Param("money")Integer money);

	SysUser findObjectByUserName(@Param("username")String username);

}
