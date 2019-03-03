package com.db.sys.service;



import com.db.common.vo.PageObject;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysRole;

/**
 * 业务层接口对象(通过此对象的方法处理日志业务)
 * @author 000
 */

public interface SysLogService {
	/**
	 * 基于查询条件分页查询当前日志信息
	 * @param username 基于条件查询时的参数名
	 * @param pageCurrent 当前的页码值
	 * @return 当前页记录+分页信息
	 */
	PageObject<SysLog> findPageObjects(String username,Integer pageCurrent);
	/**
	 * 基于id执行删除
	 * @param ids
	 * @return
	 */
	int deleteObjcts(Integer... ids);
}
