package com.sy.chainproject.download;

import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.https.APIFunction;
import com.sy.chainproject.interfance.ProgressListener;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ data  2019/3/28 14:44
 * @ author  zxcg
 */
class DownHttps {
    private APIFunction mAPIFunction;

    DownHttps(ProgressListener listener) {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
                // .addInterceptor(InterceptorUtil.HeaderInterceptor())
                .addInterceptor(new DownInterceptor(listener))//添加日志拦截器
                .build();
        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient).build();
        mAPIFunction = mRetrofit.create(APIFunction.class);
    }


    APIFunction API() {
        return mAPIFunction;
    }
}
