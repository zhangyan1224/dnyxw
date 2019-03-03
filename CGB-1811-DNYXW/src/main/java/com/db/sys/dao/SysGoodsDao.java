package com.db.sys.dao;

import com.db.sys.entity.SysGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 @author ifsy
 @create 2019年1月31日 下午2:35:13
*/
public interface SysGoodsDao {


	int validById(
            @Param("id") Integer id,
            @Param("valid") Integer valid,
            @Param("modifiedUser") String modifiedUser);

	/**
	 * 添加商品到购物车
	 * @param user_id
	 * @param shangpin_id
	 * @return
	 */
	int insertObject(@Param("user_id")Integer user_id,
					 @Param("shangpin_id") Integer shangpin_id);

	List<SysGoods> findPageObjects(@Param("youxi_name")String youxi_name,
								   @Param("youxi_qufu")String youxi_qufu,
								   @Param("shangpin_type")String shangpin_type,
								   @Param("shangpin_zhuangtai") String shangpin_zhuangtai,
								   @Param("youxi_zhanghao") String youxi_zhanghao,
								   @Param("startIndex") Integer startIndex,
								   @Param("pageSize") Integer pageSize);

	List<SysGoods> findPageObjectsOrderByjiageDesc(@Param("youxi_name")String youxi_name,
												   @Param("youxi_qufu")String youxi_qufu,
												   @Param("shangpin_type")String shangpin_type,
												   @Param("shangpin_zhuangtai") String shangpin_zhuangtai,
												   @Param("youxi_zhanghao") String youxi_zhanghao,
												   @Param("startIndex") Integer startIndex,
												   @Param("pageSize") Integer pageSize,
												   @Param("priceOrder")Integer priceOrder);

	List<SysGoods> findPageObjectsOrderByjiageAsc(@Param("youxi_name")String youxi_name,
												  @Param("youxi_qufu")String youxi_qufu,
												  @Param("shangpin_type")String shangpin_type,
												  @Param("shangpin_zhuangtai") String shangpin_zhuangtai,
												  @Param("youxi_zhanghao") String youxi_zhanghao,
												  @Param("startIndex") Integer startIndex,
												  @Param("pageSize") Integer pageSize,
												  @Param("priceOrder")Integer priceOrder);

	int getRowCount(@Param("youxi_name")String youxi_name,
					@Param("youxi_qufu")String youxi_qufu,
					@Param("shangpin_type")String shangpin_type,
					@Param("shangpin_zhuangtai") String shangpin_zhuangtai,
					@Param("youxi_zhanghao") String youxi_zhanghao);

	List<SysGoods> findGamenames();
	List<SysGoods> findGamequfus(@Param("youxi_name")String youxi_name);

}
