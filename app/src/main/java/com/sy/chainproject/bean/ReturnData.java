package com.sy.chainproject.bean;

/**
 * @ data  2019/8/7 10:04
 * @ author  zxcg
 * 退货
 */
public class ReturnData {

    /**
     * userid : 1000
     * backStatus : 1
     * d1 : 2019-5-1
     * d2 : 2019-8-6
     * storeNum :
     * styleNo :
     * styleName :
     * seasonName :
     * brandName :
     * batchNum :
     *
     */

    private int userid;
    private int backStatus;
    private String d1;
    private String d2;
    private String storeNum;
    private String styleNo;
    private String styleName;
    private String seasonName;
    private String brandName;
    private String batchNum;
    private String  storeid;//门店id

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(int backStatus) {
        this.backStatus = backStatus;
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

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
}
