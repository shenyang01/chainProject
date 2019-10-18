package com.sy.chainproject.bean;

/**
 * @ data  2019/6/6 10:24
 * @ author  zxcg
 * POS零售接口
 */
public class RetailData {
    private int userid;
    private int qty;
    private String barCode;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
