package com.jinxh.demo.present;

import android.text.TextUtils;


import com.jinxh.demo.R;
import com.jinxh.demo.base.BasePresent;
import com.jinxh.demo.model.api.APIService;
import com.jinxh.demo.model.api.ApiCallBack;
import com.jinxh.demo.model.api.RetrofitClient;
import com.jinxh.demo.model.api.BaseSubscriber;
import com.jinxh.demo.model.bean.UserInfo;
import com.jinxh.demo.ui.activity.LoginActivity;
import com.jinxh.demo.utils.FormatCheckUtils;

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


        mCurrentView.showLoading();
        addIOSubscription(RetrofitClient.builderRetrofit().create(APIService.class).login(mobileNo, password),new BaseSubscriber<>(new ApiCallBack<UserInfo>() {
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
//        mCompositeSubscription.add(RetrofitClient.builderRetrofit().create(APIService.class).login(mobileNo, password)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(Schedulers.io())
//                        .doOnNext(new Action1<HttpBean<UserInfo>>() {
//                            @Override
//                            public void call(HttpBean<UserInfo> userInfoHttpBean) {
//                                if (userInfoHttpBean.isSuccess()) {
//                                    // TODO 保存用户信息
//                                }
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<HttpBean<UserInfo>>() {
//                            @Override
//                            public void onCompleted() {
//                                mCurrentView.dismissLoading();
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                mCurrentView.dismissLoading();
//                                mCurrentView.showNetError();
//                                e.printStackTrace();
//                            }
//
//                            @Override
//                            public void onNext(final HttpBean<UserInfo> userInfoHttpBean) {
//                                if (userInfoHttpBean.isSuccess()) {
//                                    mCurrentView.loginSuccess();
//                                } else {
//                                    mCurrentView.showMessage(userInfoHttpBean.getMsg());
//                                }
//                            }
//                        })
//        );
}

}
