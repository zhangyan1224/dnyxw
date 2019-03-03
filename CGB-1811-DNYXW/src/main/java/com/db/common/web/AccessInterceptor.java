package com.db.common.web;

import java.rmi.ServerException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.db.common.exception.ServiceException;

//spring mvc 中的拦截器 要实现时间访问限制
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 此方法会在控制层方法执行前执行 此方法返回值决定是否继续执行控制层方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		// 限定时间
		long time = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		long start = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 25);
		long end = c.getTimeInMillis();
		if (time < start || time > end)
			throw new ServiceException("不在访问时间内");
		return true;// true 表示放行 false表示拦截
	}

}
