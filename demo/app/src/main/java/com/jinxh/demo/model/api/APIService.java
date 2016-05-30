package com.jinxh.demo.model.api;

import com.jinxh.demo.model.bean.HttpBean;
import com.jinxh.demo.model.bean.UserInfo;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jinxh on 16/2/17.
 */
public interface APIService {
    @GET("user/login.do")
    Observable<HttpBean<UserInfo>> login(@Query("phone") String loginName, @Query("pwd") String password);
}
