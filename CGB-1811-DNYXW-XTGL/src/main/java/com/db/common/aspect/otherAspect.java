package com.db.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
/**
 * AOP切面编程
 * @author asus
 */
@Order(2)
@Aspect
@Service
public class otherAspect {
	/**
	 * @Pointcut 注解用于定义切入点表达式
	 */
	@Pointcut("bean(sysUserServiceImpl)")
	public void pointCut(){}
	
	//通过引用@Pointcut修饰的方法来使用切入点表达式来提高可维护性
	@Before("pointCut()")
	public void bfm(){
		System.out.println("@Before");
	}
	@After("pointCut()")
	public void afm(){
		System.out.println("@After");
	}
	@AfterReturning("pointCut()")
	public void ar(){
		System.out.println("@AfterReturning");
	}
	@AfterThrowing("pointCut()")
	public void at(){
		System.out.println("@AfterThrowing");
	}
	@Around("pointCut()")
	public Object Around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("@Around before");
		Object result = jp.proceed();// 执行目标方法
		System.out.println("@Around after");
		return result;
	}
}
