package com.jinxh.demo.model.api;


import com.jinxh.demo.AppContext;
import com.jinxh.demo.model.bean.ResponseBean;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by jinxh on 16/5/30.
 */
public class ApiSubscriber<T> extends Subscriber<ResponseBean<T>> {

    public static int UNKNOWN_CODE = -1;
    private ApiCallBack<T> apiCallback;

    public ApiSubscriber(ApiCallBack<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onStart() {
        super.onStart();
        apiCallback.onStart();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException || e instanceof UnknownHostException) {
            apiCallback.onFailure(UNKNOWN_CODE, AppContext.NET_ERROR_MSG);
        } else {
            apiCallback.onFailure(UNKNOWN_CODE, e.getMessage());
        }
        apiCallback.onCompleted();
    }
    @Override
    public void onNext(ResponseBean<T> httpBean) {
        if (httpBean.isSuccess()) {
            apiCallback.onSuccess(httpBean.getBody());
        } else {
            apiCallback.onFailure(httpBean.getCode(), httpBean.getMsg());
        }
    }
}
