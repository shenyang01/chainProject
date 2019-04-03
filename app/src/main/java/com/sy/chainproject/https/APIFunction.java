package com.sy.chainproject.https;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;


/**
 * @ company zxcg
 * @ name sy
 */
public interface APIFunction {
/*
    //获取验证码
    @FormUrlEncoded
    @POST("iss/rest/pub/common/getSMSAuthCode")
    Flowable<BaseData<String>> getCode(@FieldMap Map<String, String> map);*/

    //下载文件
    @GET
    Observable<ResponseBody> downLoad(@Url String url);
}
