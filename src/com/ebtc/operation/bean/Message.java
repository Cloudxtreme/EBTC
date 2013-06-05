package com.ebtc.operation.bean;

/**
 * 
 * @ClassName: Message 
 * @Description: 和客户端传输消息类
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-6-1 下午12:59:56 
 *
 */
public class Message {
	
	private Action action;
	
	private Object data;
	
	public Message(){
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	
}
