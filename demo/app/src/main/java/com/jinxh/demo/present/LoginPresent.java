package com.jinxh.demo.present;



import com.jinxh.demo.base.BasePresent;
import com.jinxh.demo.model.api.APIService;
import com.jinxh.demo.model.api.ApiCallBack;
import com.jinxh.demo.model.api.RetrofitClient;
import com.jinxh.demo.model.api.ApiSubscriber;
import com.jinxh.demo.model.bean.ResponseBean;
import com.jinxh.demo.model.bean.UserInfo;
import com.jinxh.demo.ui.activity.LoginActivity;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jinxh on 16/2/1.
 */
public class LoginPresent extends BasePresent<LoginActivity> {

    public void login(final String mobileNo, String password) {
        Observable<ResponseBean<UserInfo>> observable = RetrofitClient.builderRetrofit().create(APIService.class).login(mobileNo, password);
        observable.observeOn(Schedulers.io())
                .doOnNext(new Action1<ResponseBean<UserInfo>>() {
                    @Override
                    public void call(ResponseBean<UserInfo> userInfoHttpBean) {
                        if (userInfoHttpBean.isSuccess()) {
                            // TODO 保存用户信息
                        }
                    }
                });
        addIOSubscription(observable, new ApiSubscriber<>(new ApiCallBack<UserInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(UserInfo model) {
                if (getView() != null) {
                    getView().loginSuccess();
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().showMessage(msg);
                }
            }

            @Override
            public void onCompleted() {
                if (getView() != null) {
                    getView().dismissLoading();
                }
            }
        }));
    }

}
