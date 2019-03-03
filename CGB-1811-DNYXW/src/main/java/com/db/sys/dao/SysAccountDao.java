package com.db.sys.dao;

import java.util.List;

import com.db.sys.entity.SysShangpin;
import org.apache.ibatis.annotations.Param;

import com.db.sys.entity.SysAccount;


public interface SysAccountDao {
	//根据条件查找商品信息
	List<SysShangpin> findPageObjects(@Param("youxi_name")String youxi_name,
									  @Param("username")String username,
									  @Param("youxi_qufu")String youxi_qufu,
									  @Param("shangpin_type")String shangpin_type,
									  @Param("startIndex")Integer startIndex,
									  @Param("pageSize")Integer pageSize,
									  @Param("youxi_zhanghao") String youxi_zhanghao);
	//根据条件查找数据数量
	int getRowCount(@Param("youxi_name") String youxi_name,@Param("shangpin_type") String shangpin_type,@Param("shangjia_name") String shangjia_name,@Param("shangpin_zhuangtai") String shangpin_zhuangtai);
	//添加商品信息
	int insertObject(SysAccount entity);
	//根据id查找商品信息
	SysAccount findObjectById(Integer id);
	// 修改商品信息
	int updateObject(SysAccount entity);

	int validById(@Param("id") Integer id, @Param("shangpin_zhuangtai") String shangpin_zhuangtai);

	int getRowCount1(@Param("youxi_name")String youxi_name,
					 @Param("username")String username,
					 @Param("youxi_qufu")String youxi_qufu,
					 @Param("shangpin_type")String shangpin_type,
					 @Param("youxi_zhanghao") String youxi_zhanghao);

	List<SysShangpin> findGamenames();
	List<SysShangpin> findGamequfus(@Param("youxi_name")String youxi_name);


}
