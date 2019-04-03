package com.sy.chainproject.download;

import android.support.annotation.NonNull;
import com.sy.chainproject.interfance.ProgressListener;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

/**
 * @ company zxcg
 * @ name sy
 * 下载文件日志拦截器
 */
public class DownInterceptor implements Interceptor {
    private ProgressListener progressListener;

    DownInterceptor(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(new DownResponseBody(response.body(), progressListener)).build();
    }
}
