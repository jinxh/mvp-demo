package com.jinxh.demo.model.bean;

/**
 * Created by jinxh on 16/2/17.
 */
public class ResponseBean<T> {
    private boolean success;
    private int code;
    private String msg;
    private T body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T data) {
        this.body = data;
    }
}
