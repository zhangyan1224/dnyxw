package com.db.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.db.common.annotation.RequiredLog;
import com.db.common.util.IPUtils;
import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysUser;

/**
 * @Aspect 修饰的类表示一个切面对象,此对象中可以封装我们要植入的扩展功能
 * @Service
 * @Order(1) 用于指定切面优先级,数字越小优先级越高
 * @author asus
 */
@Order(1)
@Aspect
@Service
public class SysLogAspect {
	/**
	 * @Around 注解描述的方法为一个环绕通知(用户封装和织入扩展功能),此通知可以在目标方法执行之前和之后添加额外 的业务操作
	 *         其中注解中的内容为一个切入点表达式,通过此切入点表达式 告诉spring何时去执行
	 * @param jp
	 *            表示连接点对象(封装了具体目标方法信息)
	 * @return 返回目标方法的执行结果
	 * @throws Throwable
	 */
	@Autowired
	private SysLogDao sysLogDao;
	
	/**
	 * @Pointcut 注解用于定义切入点表达式
	 */
	//@Pointcut("bean(*ServiceImpl)")
	//public void pointCut() {}

	//通过引用@Pointcut修饰的方法来使用切入点表达式来提高可维护性
	//@Around("pointCut()")
	@Around("@annotation(com.db.common.annotation.RequiredLog)")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("log around");
		long t1 = System.nanoTime();
		Object result = jp.proceed();// 执行目标方法
		long t2 = System.nanoTime();
		Save(jp, t2 - t1);
		return result;
	}

	private void Save(ProceedingJoinPoint jp, long time) throws NoSuchMethodException, SecurityException {
		// 1.获取用户的操作日志
		// 1.1获取登录用户
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String username = user.getUsername();
		// 1.2获取目标方法的方法名
		Class<?> targetClass = jp.getTarget().getClass();
		String pkgClassName = targetClass.getName();
		MethodSignature ms = (MethodSignature) jp.getSignature();// 方法签名
		String method = pkgClassName + "." + ms.getName();
		// 1.3获取执行目标方法时传入的实际参数
		Object[] args = jp.getArgs();
		String params = Arrays.toString(args);
		// 1.4获取操作名
		// 1.4.1获取方法对象
		Method targetMethod = targetClass.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		// 判定目标方法上是否有RequestLog注解
		boolean flag = targetMethod.isAnnotationPresent(RequiredLog.class);
		// 假如目标方法对象上有注解,我们获取注解定义的操作值
		String operation ="";
		if (flag) {
			// 1.4.2获取方法对象上的注解
			RequiredLog RequiredLog = targetMethod.getDeclaredAnnotation(RequiredLog.class);
			// 1.4.3获取注解中定义的操作名
			operation = RequiredLog.value();
		}
		// 1.5获取ip地址
		// 2.封装操作日志
		SysLog log = new SysLog();
		log.setUsername(username);
		log.setTime(time);
		log.setOperation(operation);
		log.setMethod(method);
		log.setParams(params);
		log.setIp(IPUtils.getIpAddr());
		log.setCreatedTime(new Date());
		// 3.保存操作日志(写入到数据库)
		sysLogDao.insertObject(log);
	}
}
