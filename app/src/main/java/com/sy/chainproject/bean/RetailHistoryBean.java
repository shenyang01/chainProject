package com.sy.chainproject.bean;

/**
 * @ data  2019/7/3 16:27
 * @ author  zxcg
 */
public class RetailHistoryBean {

    /**
     * posid : 1906261128311000
     * shopid : 0
     * currency : RMB
     * totalQty : 1
     * totalAmount : 6198
     * posAmount : 0.0
     * userid : 0
     * dtime : 2019-06-26 11:28
     * IMEI :
     * flag : 0
     * backStatus: 0  退单标志
     */

    private String posid;
    private int shopid;
    private String currency;
    private String totalQty;
    private String totalAmount;
    private String posAmount;
    private int userid;
    private String dtime;
    private String IMEI;
    private int flag;
    private int backStatus;

    public int getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(int backStatus) {
        this.backStatus = backStatus;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPosAmount() {
        return posAmount;
    }

    public void setPosAmount(String posAmount) {
        this.posAmount = posAmount;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
