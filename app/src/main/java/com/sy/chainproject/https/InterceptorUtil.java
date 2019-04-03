package com.sy.chainproject.https;

import android.util.Log;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * @ company zxcg
 * @ name sy
 */
public class InterceptorUtil {

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.e("tag", "tag: " + message)).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }
}
