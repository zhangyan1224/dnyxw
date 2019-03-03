package com.db.common.java.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 
 * @author asus
 *模拟mybatis中如何根据接口直接产生代理对象
 */
interface SearchDao{
	String search(String key);
}
//创建代理对象工厂
/**
 * <T>应用在方法左侧表示这个方法时泛型方法
 * @author asus
 *
 */
class DaoProxyFactory{
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> tClass) {
		return (T) Proxy.newProxyInstance(tClass.getClassLoader(),new Class[] {tClass}, new DaoProxyHandler(tClass));
	}
}
//扩展业务处理器
class DaoProxyHandler implements InvocationHandler{
	//声明目标对象
	private Class<?> target;
	public DaoProxyHandler(Class<?> target){
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		 //通过反射执行目标对象的目标方法.
		System.out.println("invoke ");
		String namespace=target.getName();
		String methodId=method.getName();
		System.out.println(namespace+"."+methodId);
		//.......session
		return null;
	}
}
//基于JDK的代理机制为目标接口SearchDao创建代理对象.
public class TestProxy03 {
	public static void main(String[] args) {
		DaoProxyFactory factory=new DaoProxyFactory();
		SearchDao dao=factory.getProxy(SearchDao.class);
	    System.out.println(dao.getClass().getName());
	    dao.search("cgb1811");
	}
}

