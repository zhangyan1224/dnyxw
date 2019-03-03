package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.common.vo.CheckBox;
import com.db.common.vo.SysRoleMenuVo;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysRole;

/**
 * 这个类用来封装接口
1、	Mapper接口方法名和mapper.xml中定义sql的id值相同
2、	Mapper接口方法接收的参数类型和mapper.xml中定义的sql 的parameterType的类型相同
3、	Mapper接口方法的返回值类型和mapper.xml中定义的sql的resultType的类型相同
 */
public interface SysRoleDao {
	/**
	 * 基于条件分页查询日志信息
	 * @param name  查询条件(例如查询哪个角色的信息)
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的日志记录信息
	 * 数据库中每条日志信息封装到一个SysRole对象中
	 */
	List<SysRole> findPageObjects(@Param("name")String name,
								@Param("startIndex")Integer startIndex,
								@Param("pageSize")Integer pageSize
								);
	/**
	 * 基于条件查询总记录数
	 * @param name 查询条件(例如查询哪个角色的信息)
	 * @return 总记录数(基于这个结果可以计算总页数)
	 * 说明：假如如下方法没有使用注解修饰，在基于名字进行查询
	 * 时候会出现There is no getter for property named
	 * 'name' in 'class java.lang.String'
	 */
	int getRowCount(@Param("name")String name);
	/**
	 * 基于SysRole对象中的数据记性添加操作
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);
	/**
	 * 基于id删除角色信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 基于角色id查询角色表中数据及其所对应的菜单id封装成vo值对象
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	/**
	 * 基于页面修改的数据对象进行修改操作
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	/**
	 * 查询角色id和名字
	 * @return
	 */
	List<CheckBox> findObjects();
}
