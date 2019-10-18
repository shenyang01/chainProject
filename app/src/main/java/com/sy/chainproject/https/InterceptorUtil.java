package com.sy.chainproject.https;

import com.sy.chainproject.utils.LogUtils;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * @ company zxcg
 * @ name sy
 */
public class InterceptorUtil {

    //日志拦截器
     static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(message -> LogUtils.e("tag", "tag: " + message)).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }
}
