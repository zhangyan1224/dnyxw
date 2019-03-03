package com.db.sys.service;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysGoods;
import com.db.sys.entity.SysShangpin;
import com.db.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 @author ifsy
 @create 2019年1月31日 下午2:54:22
*/
public interface SysCartService {

	List<Integer> findShangpinIdsByUserId();

	PageObject<SysShangpin> finObjectByShangpinIds(Integer[] shangpin_ids,
			   Integer user_id,
			   String youxi_name,
			   String youxi_qufu,
			   String shangpin_type,
			   Integer pageCurrent);
	int deleteObject(Integer user_id,Integer shangpin_id);
	
	int doTianjiaById(Integer user_id,Integer id);
	
	List<SysShangpin> findGamenames();
	List<SysShangpin> findGamequfus(String youxi_name);
	
}
