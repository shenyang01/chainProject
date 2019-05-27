package com.sy.chainproject.bean;

/**
 * @ data  2019/5/16 16:55
 * @ author  zxcg
 * 收货上传参数
 */
public class ReceivingNoteData {
   private String userid;//用户id，不能为空
   private String shopid;//门店id，不能为空
   private String  d1;//起始日期
   private String  d2;//结束日期
   private String  storeNum;//总部发货单号，为搜索条件，可以为空，空用0代替
    private String  styleNo;//款号，为搜索条件，可以为空，空用0代替
    private String  storeid;//结束日期

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getStyleNo() {
        return styleNo;
    }

    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }
}
