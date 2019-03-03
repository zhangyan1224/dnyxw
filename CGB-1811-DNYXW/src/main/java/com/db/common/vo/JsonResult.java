package com.db.common.vo;

/**
 @author ifsy
 @create 2019年1月25日 上午11:40:24
 * VO:封装控制层要返回到客户端的数据
 */
public class JsonResult {
	/** 我们自己设置的服务端向客户端输出的状态码 */
	private int state = 1;//"1,ok;0,false"
	/** 状态码对应的具体信息 */
	private String message = "ok";
	/** 业务层返回给数据层的具体数据 */
	private Object data;
	
	
	public JsonResult() {
	}
	public JsonResult(Object data) {
		this.data = data;
	}
	public JsonResult(String message) {
		this.message = message;
	}
	public JsonResult(Throwable e) {
		this.state = 0;
		this.message = e.getMessage();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
