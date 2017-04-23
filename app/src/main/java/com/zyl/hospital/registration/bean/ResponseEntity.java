package com.zyl.hospital.registration.bean;

/**
 * 返回给客户端的实体,自动映射为json
 * @author Administrator
 *
 * @param <T>
 */
public class ResponseEntity<T> {
	/** 状态 */
	public int status;
    /** 返回信息*/
    public String msg;
    /** 返回数据*/
    public T data;
	public ResponseEntity() {
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
    
    
}
