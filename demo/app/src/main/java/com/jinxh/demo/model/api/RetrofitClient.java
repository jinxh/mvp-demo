package com.jinxh.demo.model.api;

import com.jinxh.demo.AppContext;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jinxh on 16/2/17.
 */
public class RetrofitClient {
    private static Retrofit mRetrofit;

    private RetrofitClient() {

    }

    public static Retrofit builderRetrofit() {
        if (mRetrofit == null) {
            // log
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(AppContext.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();

        }
        return mRetrofit;
    }
}
