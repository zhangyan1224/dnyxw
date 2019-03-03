package com.db.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;

//全局异常处理类，在此类中可以定义具体的异常处理
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult doHandleShiroException(ShiroException e) {
		e.printStackTrace();
		JsonResult r = new JsonResult();
		r.setState(0);
		if (e instanceof UnknownAccountException) {
			r.setMessage("账户信息不存在");
		} else if (e instanceof LockedAccountException) {
			r.setMessage("账户被禁用");
		} else if (e instanceof CredentialsException) {
			r.setMessage("密码不正确");
		} else if (e instanceof AuthorizationException) {
			r.setMessage("无授权");
		} else {
			r.setMessage("认证或授权失败");
		}
		return r;
	}

	// JDK 自带的日志api
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException(RuntimeException e) {
		e.printStackTrace();// 也可以写日志
		return new JsonResult(e);// 封装异常信息
	}
}
