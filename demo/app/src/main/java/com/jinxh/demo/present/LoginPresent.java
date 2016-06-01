package com.jinxh.demo.present;

import android.text.TextUtils;


import com.jinxh.demo.R;
import com.jinxh.demo.base.BasePresent;
import com.jinxh.demo.model.api.APIService;
import com.jinxh.demo.model.api.ApiCallBack;
import com.jinxh.demo.model.api.RetrofitClient;
import com.jinxh.demo.model.api.ApiSubscriber;
import com.jinxh.demo.model.bean.ResponseBean;
import com.jinxh.demo.model.bean.UserInfo;
import com.jinxh.demo.ui.activity.LoginActivity;
import com.jinxh.demo.utils.FormatCheckUtils;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jinxh on 16/2/1.
 */
public class LoginPresent extends BasePresent<LoginActivity> {


    public void login(final String mobileNo, String password) {
        if (TextUtils.isEmpty(mobileNo)) {
            mCurrentView.showMessage(R.string.alert_null_mobile);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mCurrentView.showMessage(R.string.alert_null_password);
            return;
        }
        if (!FormatCheckUtils.checkMobileNumberValid(mobileNo)) {
            mCurrentView.showMessage(R.string.alert_no_mobile);
            return;
        }

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
                mCurrentView.showLoading();
            }

            @Override
            public void onSuccess(UserInfo model) {
                mCurrentView.loginSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                mCurrentView.showMessage(msg);
            }

            @Override
            public void onCompleted() {
                mCurrentView.dismissLoading();
            }
        }));
    }

}
