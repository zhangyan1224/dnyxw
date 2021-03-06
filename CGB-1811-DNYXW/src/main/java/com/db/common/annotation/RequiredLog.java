package com.db.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解 
//1.JDK1.5推出的新特性
//2.Java中一种元数据（描述数据的数据，类似xml）
//3.Java中的一种特殊的class（注解最终会编译成.class文件）
//注解的定义
//1.使用@interface单词进行定义
//注解的应用
//1.描述类及其成员
//2.可以定义时指定何时有效
//自定义注解
//1.@target 用于描述注解可以修饰那些成员
//2.@retention 用于描述注解何时有效
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
@Target(ElementType.METHOD) // 描述一个方法 例如（{ElementType.METHOD,ElementType.Type}）
public @interface RequiredLog {
	// 理解为注解内部定义的属性
	String value() default "";

}
