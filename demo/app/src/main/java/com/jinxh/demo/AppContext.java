package com.jinxh.demo;

import android.app.Application;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;


/**
 * Created by jinxh on 15/11/19.
 * QQ:123489504
 */
public class AppContext extends Application {

    public final static String API_BASE_URL = "http://appgd.futao.me:80/fastorder/";
    // 配置是否要沉浸式头部
    public final static boolean HIDDEN_STATUS_BAR = false;
    public static String NET_ERROR_MSG;

    @Override
    public void onCreate() {
        super.onCreate();
        // 捕捉应用异常信息
        CustomActivityOnCrash.install(this);
        initConstant();
    }

    private void initConstant() {
        NET_ERROR_MSG = getString(R.string.alert_net_error);
    }
}
