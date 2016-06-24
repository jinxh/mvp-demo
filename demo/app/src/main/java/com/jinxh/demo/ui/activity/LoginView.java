package com.jinxh.demo.ui.activity;

import com.jinxh.demo.base.BaseMvpView;
import com.jinxh.demo.model.bean.UserInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface LoginView extends BaseMvpView{
    void loginSuccess();
    void saveUserInfo(UserInfo userInfo);
}
