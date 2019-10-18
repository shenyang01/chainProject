package com.sy.chainproject.https;

import com.sy.chainproject.bean.*;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.List;
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

    //发货单类型
    @GET("rest/get_erp_sysStandardInfo/33/{userid}")
    Observable<List<ReasonBean>> getReceivingDetailType(@Path("userid") int userid);

    //总部发货单详情修改提交
    @FormUrlEncoded
    @POST("rest/Add_erp_InStyleShopInfo")
    Observable<BaseData<ReceivingDetailBean>> getReceivingDetailUpdate(@FieldMap Map<String, String> map);


    // 款名和款号查询
    @FormUrlEncoded
    @POST("rest/get_erp_styleShopStoreInfo")
    Observable<BaseData<RetailBean>> getGoodsInfo(@FieldMap Map<String, String> map);

    // POS零售扫码
    @FormUrlEncoded
    @POST("rest/get_erp_sellFromBarCodeInfo")
    Observable<BaseData<RetailBean>> getRetailData(@FieldMap Map<String, String> map);

    // POS零售提交   退单是为1  pos提交为0
    @FormUrlEncoded
    @POST("rest/Add_erp_ShopPosInfo")
    Observable<BaseData<RetailBean>> getPosData(@FieldMap Map<String, String> map);

    // POS零售历史
    @GET("rest/get_erp_PosHistoryList")
    Observable<BaseData<RetailHistoryBean>> getPosHistory(@QueryMap Map<String, String> map);

    // POS零售历史明细
    @GET("rest/get_erp_PosHistoryDetail")
    Observable<BaseData<RetailBean>> getPosHistoryDetail(@QueryMap Map<String, String> map);

    // POS零售历史明细退单原因
    @GET("rest/get_erp_sysStandardInfo/21/{userid}")
    Observable<List<ReasonBean>> getPosHistoryReason(@Path("userid") int userid);

    // 盘点创建订单
    @FormUrlEncoded
    @POST("rest/Appy_erp_MkInventory")
    Observable<BaseData<InventoryBean>> getInventory(@FieldMap Map<String, String> map);

    // 门店盘点获取某盘点单明细接口
    @GET("rest/get_erp_MkInventory")
    Observable<BaseData<InventoryBean>> getInventoryDetail(@QueryMap Map<String, String> map);

    // 门店盘点修改实盘数接口
    @FormUrlEncoded
    @POST("rest/set_erp_MkInventory")
    Observable<BaseData<InventoryBean>> setInventoryDetail(@FieldMap Map<String, String> map);


    // 门店月报盘点历史数据接口
    @GET("rest/get_erp_MkInventoryHistoryList")
    Observable<BaseData<HistoryBean>> getInventoryHistoryList(@QueryMap Map<String, String> map);


    // 月报创建订单
    @FormUrlEncoded
    @POST("rest/Appy_erp_MonthlyReport")
    Observable<BaseData<MonthlyBean>> getMonthly(@FieldMap Map<String, String> map);

    // 门店月报获取明细接口
    @GET("rest/get_erp_MonthlyReport")
    Observable<BaseData<MonthlyBean>> getMonthlyDetail(@QueryMap Map<String, String> map);



    // 现金详情获取
    @GET("rest/get_erp_CashBank")
    Observable<BaseData<CashBankBean>> getCashBank(@QueryMap Map<String, String> map);

    //现金银行提交接口
    @FormUrlEncoded
    @POST("rest/Apply_erp_CashBank")
    Observable<BaseData<CashBankBean>> setCashBank(@FieldMap Map<String, String> map);


    //VIP 新增用户
    @FormUrlEncoded
    @POST("rest/Apply_erp_vipInfo")
    Observable<BaseData<String>> addVipInfo(@FieldMap Map<String, String> map);

    //VIP 注册档案查询
    @GET("rest/get_erp_vipRegInfo")
    Observable<BaseData<VIPBean>> getVipRegInfo(@QueryMap Map<String, String> map);

    //VIP 获取卡别
    @GET("rest/get_erp_sysStandardInfo/32/{userid}")
    Observable<List<ReasonBean>> getVipCardType(@Path("userid") int userid);


    //调拨订单
    @GET("rest/get_erp_TransferStoreInfo/-1")
    Observable<BaseData<TransshipmentBean>> getTransferStoreInfo(@QueryMap Map<String, String> map);

    //调拨单类型
    @GET("rest/get_erp_sysStandardInfo/34/{userid}")
    Observable<List<ReasonBean>> getTransferStoreType(@Path("userid") int userid);

    //调拨单季节
    @GET("rest/get_erp_sysStandardInfo/4/{userid}")
    Observable<List<ReasonBean>> getTransferStoreSeason(@Path("userid") int userid);

    //调拨单品牌
    @GET("rest/get_erp_sysStandardInfo/5/{userid}")
    Observable<List<ReasonBean>> getTransferStoreBrand(@Path("userid") int userid);

    //调拨单返回数据
    @GET("rest/get_erp_styleInfo/-2")
    Observable<BaseData<TransshipmentDetailBean>> get_erp_styleInfo(@QueryMap Map<String, String> map);


    //退货
    @GET("rest/get_erp_InShopStoreInfo")
    Observable<BaseData<ReturnBean>> get_erp_InShopStoreInfo(@QueryMap Map<String, String> map);

    //退货详情
    @GET("rest/get_erp_InShopStoreDetail")
    Observable<BaseData<ReturnGoodsDetailBean>> get_erp_InShopStoreDetail(@QueryMap Map<String, String> map);

    //退货修改提交
    @FormUrlEncoded
    @POST("rest/Add_erp_backShopStoreInfo")
    Observable<BaseData<String>> Add_erp_backShopStoreInfo(@FieldMap Map<String, String> map);
}