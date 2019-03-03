package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.sys.entity.SysShangpin;

/**
 * 这个类用来封装接口
1、	Mapper接口方法名和mapper.xml中定义sql的id值相同
2、	Mapper接口方法接收的参数类型和mapper.xml中定义的sql 的parameterType的类型相同
3、	Mapper接口方法的返回值类型和mapper.xml中定义的sql的resultType的类型相同
 */
public interface SysGoumaiDao {
	/**
	 * 基于条件分页查询日志信息
	 * @param username  查询条件(例如查询哪个用户的日志信息)
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的日志记录信息
	 * 数据库中每条日志信息封装到一个SysLog对象中
	 */
	List<SysShangpin> findPageObjects(@Param("goumai_name")String goumai_name,
									  @Param("startIndex")Integer startIndex,
									  @Param("pageSize")Integer pageSize
								);
	List<SysShangpin> findPageObject(@Param("youxi_name")String youxi_name,
									 @Param("goumai_name")String goumai_name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize
			);
	/**
	 * 基于条件查询总记录数
	 * @param username 查询条件(例如查询哪个用户的日志信息)
	 * @return 总记录数(基于这个结果可以计算总页数)
	 * 说明：假如如下方法没有使用注解修饰，在基于名字进行查询
	 * 时候会出现There is no getter for property named
	 * 'username' in 'class java.lang.String'
	 */
	int getRowCount(@Param("goumai_name")String goumai_name);
	int getRowCount1(@Param("youxi_name")String youxi_name,@Param("goumai_name")String goumai_name);
	/**
	 * 基于条件删除指定记录
	 * @param ids 特殊的数组
	 * @return
	 */
	int deleteObjcts(@Param("ids")Integer... ids);
}
