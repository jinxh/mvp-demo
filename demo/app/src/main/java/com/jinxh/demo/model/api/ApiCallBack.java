package com.jinxh.demo.model.api;

/**
 * Created by jinxh on 16/5/30.
 */
public abstract class ApiCallBack<T> {
    public abstract void onSuccess(T model);

    public void onStart() {

    }
    public abstract void onFailure(int code, String msg);

    public void onCompleted() {

    }
}
