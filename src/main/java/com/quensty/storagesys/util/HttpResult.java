package com.quensty.storagesys.util;

import org.springframework.http.HttpStatus;

/**
 * http请求结果封装
 * @author Laiwenjun
 * @since 2023-06-27
 */

public class HttpResult {
	
	private HttpStatus code = HttpStatus.OK;
	private String msg;
	private Object data;
	
	public static HttpResult error() {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static HttpResult error(String msg) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, msg);
	}
	
	public static HttpResult error(HttpStatus code, String msg) {
		HttpResult r = new HttpResult();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static HttpResult ok(String msg) {
		HttpResult r = new HttpResult();
		r.setMsg(msg);
		return r;
	}
	
	public static HttpResult ok(Object data) {
		HttpResult r = new HttpResult();
		r.setData(data);
		return r;
	}

	public static HttpResult ok(String msg,Object data) {
		HttpResult r = new HttpResult();
		r.setMsg(msg);
		r.setData(data);
		return r;
	}
	
	public static HttpResult ok() {
		return new HttpResult();
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
