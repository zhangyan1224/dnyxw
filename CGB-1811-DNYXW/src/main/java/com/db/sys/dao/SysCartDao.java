package com.db.sys.dao;

import com.db.sys.entity.SysGoods;
import com.db.sys.entity.SysShangpin;
import com.db.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 @author ifsy
 @create 2019年1月31日 下午2:35:13
*/
public interface SysCartDao {

	/**
	 *  根据当前用户ID从订单表中获取商品ID
	 * @param user_id
	 * @return
	 */
	List<Integer> findShangpinIdsByUserId(@Param("user_id")Integer user_id);

	/**
	 *  根据商品ID查询商品信息
	 * @param shangpin_ids
	 * @return
	 */
	List<SysShangpin> finObjectByShangpinIds(@Param("shangpin_ids")Integer[] shangpin_ids,
			 @Param("youxi_name") String youxi_name,
			 @Param("youxi_qufu") String youxi_qufu,
			 @Param("shangpin_type") String shangpin_type,
			 @Param("startIndex") Integer startIndex,
			 @Param("pageSize") Integer pageSize);


	int deleteObject(@Param("user_id")Integer user_id,
					 @Param("shangpin_id")Integer shangpin_id);
	
	int doTianjiaById(@Param("user_id")Integer user_id,
					 @Param("id")Integer id);
	List<SysShangpin> findGamenames();
	List<SysShangpin> findGamequfus(@Param("youxi_name")String youxi_name);

}
