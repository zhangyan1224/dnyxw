package com.db.common.aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class CacheAspect {
	Map<CacheKey, Object> cache = new ConcurrentHashMap<>();
	@Around("@annotation(com.db.common.annotation.RequiredCache)")
	public Object Around(ProceedingJoinPoint jp) throws Throwable{
		//1.从缓存中取
		CacheKey key=new CacheKey();
		Class<?> targetClass = jp.getTarget().getClass();
		key.setTargetClass(targetClass);
		MethodSignature ms = (MethodSignature) jp.getSignature();// 方法签名
		key.setTargetMethod(ms.getMethod());
		Object[] args = jp.getArgs();
		key.setArgs(args);
		
		Object result=cache.get(key);
		//2.缓存中没有执行目标的方法
		if (result==null) {
			result=jp.proceed();// 执行目标方法
			System.out.println("数据");
			//3.将执行的目标方法获取的结果存到缓存
			cache.put(key,result);
		}
		//4.返回结果
		return result;
	}
}
