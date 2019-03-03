package com.db.common.java.model;

/**
 * 
 * @author asus
 *没有接口如何产生代理对象
 */
class ProductServiceImpl{
	public void saveObject() {
		System.out.println("save product");
	}
}
//基于CGlib的代理机制为目标对象ProductServiceImpl创建代理对象.
public class TestProxy4 {
	public static void main(String[] args) {
	}
}

