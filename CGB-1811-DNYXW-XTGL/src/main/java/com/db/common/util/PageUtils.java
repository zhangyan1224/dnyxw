package com.db.common.util;

import java.util.List;

import com.db.common.vo.PageObject;

public class PageUtils {
	/**
	 * 当一个泛型参数应用在了方法返回值类型
	 * 左侧时这样的方法称之为泛型方法
	 * @param rowCount
	 * @param records
	 * @param pageSize
	 * @param pageCurrent
	 * @return
	 */
	public static <T> PageObject<T> newPageObject(
			Integer rowCount,
			List<T> records,
			Integer pageSize,
			Integer pageCurrent){
		//4.1)构建PageObject对象
		PageObject<T> pageObject = new PageObject<>();
		//4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);//当前页码值
		pageObject.setPageSize(pageSize);//页面大小
		pageObject.setRowCount(rowCount);//总行数
		pageObject.setRecords(records);//当前页记录
		pageObject.setPageCount((rowCount-1)/pageSize+1);//总页数=(总行数-1)/页面大小的结果再+1
		return pageObject;
	}
}
