package com.itheima.leon.bottombardemo.app.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Lancen on 2017/4/18.
 */

public class MyAppliction extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext=this;
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    public static Context getContext(){
        return mContext;
    }
}
