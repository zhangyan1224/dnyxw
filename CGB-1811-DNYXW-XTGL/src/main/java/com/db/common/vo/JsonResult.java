package com.db.common.vo;

import java.io.Serializable;
/**
 * VO:封装控制层要返回到客户端的数据
 * @author 000
 */
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 4954739213138989742L;
	/**状态码:我们自己设置的服务端向客户端输出的状态码*/
	private int state=1;//1表示SUCCESS,0表示ERROR
	/**状态码对应的具体信息*/
	private String message="ok";
	/**正确数据:业务层返回给控制层的具体数据*/
	private Object data;
	public JsonResult() {}
	public JsonResult(String message){
		this.message=message;
	}
	/**一般查询时调用，封装查询结果*/
	public JsonResult(Object data) {
		this.data=data;
	}
	/**出现异常时时调用*/
	public JsonResult(Throwable e){
		this.state=0;
		this.message=e.getMessage();
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
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
}
