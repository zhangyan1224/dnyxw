package com.db.common.java.model;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * service接口产生代理对象调用实现类
 * @author asus
 *
 */
interface SearchService{
	String search(String key);
}
class SearchServiceImpl implements SearchService{
	@Override
	public String search(String key) {
		System.out.println("search ....");
		return "search result TEDU";
	}
}

class ProxyFactory{
	  /**
	   * 代理对象处理器
	   * @author tarena
	   */
	    static class ProxyHandler implements InvocationHandler{
		   private Object target;
		   //代理类构造方法
		   public ProxyHandler(Object target) {
			 this.target=target;
		   }
		   /**
		    * @param proxy 代理对象
		    * @param method 目标方法
		    * @param args  实际参数
		    * @return 目标方法的执行结果
		    * @throws Throwable
		    */
		   @Override
		   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				 long t1=System.nanoTime();
				 //通过反射执行目标对象的目标方法.
				 Object result=method.invoke(target, args);
				 long t2=System.nanoTime();
				 System.out.println("time:"+(t2-t1));
				 return result;
		   }
	  }
      /**
       * 为目标对象创建代理对象
       * @param target 目标对象
       * @return 代理对象
       */
	  public static Object newProxy(Object target){
		  return  Proxy.newProxyInstance(
				  target.getClass().getClassLoader(),//loader 为类加载器对象
				  target.getClass().getInterfaces(),//要实现接口对象
				  new ProxyHandler(target));//处理器(扩展业务及方法调用)
	  }
}
public class TestProxy02 {
	public static void main(String[] args) {
		doMethod01();
		doMethod02();
		
	}
    private static void doMethod02() {
		SearchServiceImpl target = new SearchServiceImpl();
		Object Proxy = ProxyFactory.newProxy(target);
		SearchService ss=(SearchService)Proxy;
		ss.search("nihao");
	}
	//alt+shift+m 可以快速将一段代码提取为方法
	private static void doMethod01() {
		//1.创建目标对象
		MessageServiceImpl target=
		new MessageServiceImpl();
		//2.为目标对象创建代理对象(代理对象会与目标对象实现共同的接口)
		Object proxy=ProxyFactory.newProxy(target);
		//3.执行代理对象的方法
		MessageService ms=(MessageService)proxy;
		System.out.println(ms.getClass().getName());
		ms.sendMsg("hello everyone");
	}
}










