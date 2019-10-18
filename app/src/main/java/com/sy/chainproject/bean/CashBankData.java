package com.sy.chainproject.bean;

/**
 * @ data  2019/7/19 15:00
 * @ author  zxcg
 * 现金银行接口参数
 */
public class   CashBankData {

    /**
     * mkey : 10
     * userid : 1000
     * cashItem : 1
     * Amount : -90
     * remark : 2333
     *
     * "D1": "2019-4-1",
     * 	"D2": "2019-7-16"
     */

    private int mkey;
    private int userid;
    private int cashItem;
    private String Amount;
    private String remark;
    private String D1;
    private String D2;

    public String getD1() {
        return D1;
    }

    public void setD1(String d1) {
        D1 = d1;
    }

    public String getD2() {
        return D2;
    }

    public void setD2(String d2) {
        D2 = d2;
    }

    public int getMkey() {
        return mkey;
    }

    public void setMkey(int mkey) {
        this.mkey = mkey;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCashItem() {
        return cashItem;
    }

    public void setCashItem(int cashItem) {
        this.cashItem = cashItem;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
