package com.db.common.util;

import java.util.List;
import com.db.common.vo.PageObject;

public class PageUtils {
	/**
	 * 当一个泛型参数应用在方法的返回值类型左侧时这样的方法叫做泛型方法
	 * 
	 * @return 当一个泛型参数应用在类名右侧表示这个类是泛型类
	 */
	public static <T> PageObject<T> newPageObject(Integer rowCount, List<T> records, Integer pageSize,
			Integer pageCurrent) {

		PageObject<T> po = new PageObject<>();
		po.setRowCount(rowCount);
		po.setRecords(records);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		po.setPageCount((rowCount - 1) / pageSize + 1);
		return po;
	}
}
