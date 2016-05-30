package com.jinxh.demo;

import android.app.Application;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;


/**
 * Created by jinxh on 15/11/19.
 * QQ:123489504
 */
public class AppContext extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // 捕捉应用异常信息
        CustomActivityOnCrash.install(this);

    }

}
