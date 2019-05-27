package com.sy.chainproject.https;

import com.sy.chainproject.bean.*;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.Map;


/**
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

    // 上传数据

    /**
     * MultipartBody.Part[] parts = new MultipartBody.Part[media.size()];
     * int cnt = 0;
     * for (String m : media) {
     * File file = new File(m);
     * RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
     * MultipartBody.Part filePart = MultipartBody.Part.createFormData("headimg[]", file.getName(), requestFile);
     * parts[cnt] = filePart;
     * cnt++;
     * }
     */
    @POST("")
    @Multipart
    Observable<ResponseBody> upload(@PartMap MultipartBody.Part[] parts);

    //登录
    @FormUrlEncoded
    @POST("rest/get_erp_LogValidateInfo")
    Observable<BaseData<UserBean>> login(@FieldMap Map<String, String> map);

    //显示总部发货单接口
    @FormUrlEncoded
    @POST("rest/get_erp_OutShopDialogInfo")
    Observable<BaseData<ReceivingNoteBean>> getReceivingNote(@FieldMap Map<String, String> map);
    //显示总部发货单详情
    @FormUrlEncoded
    @POST("rest/get_erp_OutShopDialogdetail")
    Observable<BaseData<ReceivingDetailBean>> getReceivingDetail(@FieldMap Map<String, String> map);


    //总部发货单详情修改提交
    @FormUrlEncoded
    @POST("rest/Add_erp_InStyleShopInfo")
    Observable<BaseData<ReceivingDetailBean>> getReceivingDetailUpdata(@FieldMap Map<String, String> map);

    // 款名和款号查询
    @FormUrlEncoded
    @POST("rest/get_erp_styleShopStoreInfo")
    Observable<BaseData<QueryBean>> getGoodsInfo(@FieldMap Map<String, String> map);
}