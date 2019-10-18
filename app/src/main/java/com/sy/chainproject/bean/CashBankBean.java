package com.sy.chainproject.bean;

/**
 * @ data  2019/7/19 14:26
 * @ author  zxcg
 * 现金银行
 */
public class CashBankBean {

    /**
     * mkey : 2
     * userid : 1000
     * cashItem : 20
     * Amount : 3000
     * Balance : 2172
     * cashItemName : 期初现金
     * username : Admin
     * remark : 8999
     * dtime : 2019-07-16 19:59:19.0
     *
     * state: 状态，自定义  修改和确定
     */

    private int mkey;
    private int userid;
    private int cashItem;
    private int Amount;
    private int Balance;
    private String cashItemName;
    private String username;
    private String remark;
    private String dtime;
    private String state ="修改";   // 提交 修改状态


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    public String getCashItemName() {
        return cashItemName;
    }

    public void setCashItemName(String cashItemName) {
        this.cashItemName = cashItemName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }
}
