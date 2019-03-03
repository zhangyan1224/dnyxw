package com.db.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.db.common.vo.JsonResult;
/**
 * @ControllerAdvice 注解描述的类
 * 为一个全局异常处理类,此类中可以定义具体的异常处理方法处理相关异常
 * @author 000
 */
//@ControllerAdvice
@RestControllerAdvice//等效于@ResponseBody应用于全部方法
public class GlobalExceptionHandler {
	//JDK中的自带的日志API
	/**
	 * @ExceptionHandler 注解描述的方法为一个异常处理方法
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	//@ResponseBody
	public JsonResult doHandleRuntimeException(RuntimeException e) {
		e.printStackTrace();//也可以写日志
		return new JsonResult(e);//封装异常信息
	}
	@ExceptionHandler(ShiroException.class)
	//@ResponseBody
	public JsonResult doHandleShiroException(ShiroException e) {
		e.printStackTrace();//也可以写日志
		JsonResult r = new JsonResult();
		r.setState(0);
		if (e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		}else if (e instanceof LockedAccountException) {
			r.setMessage("账户被锁定");
		}else if (e instanceof CredentialsException) {
			r.setMessage("密码不正确");
		}else if (e instanceof AuthorizationException) {
			r.setMessage("没有此操作权限");
		}else {
			r.setMessage("认证或授权失败");
		}
		return r;//封装异常信息
	}
	
}
