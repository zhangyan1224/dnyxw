package com.db.sys.service;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysGoods;

import java.util.List;
import java.util.Map;

/**
 @author ifsy
 @create 2019年1月31日 下午2:54:22
*/
public interface SysGoodsService {

	Map<String, Object> findObjectById(Integer userId);

	int validById(Integer id, Integer valid, String modifiedUser);

	PageObject<SysGoods> findPageObjects(String youxi_name,String youxi_qufu,String shangpin_type,String shangpin_zhuangtai,String youxi_zhanghao,Integer pageCurrent,Integer priceOrder);

	int saveObject(Integer user_id,Integer shangpin_id);

	List<SysGoods> findGamenames();
	List<SysGoods> findGamequfus(String youxi_name);
}
