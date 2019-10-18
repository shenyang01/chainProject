package com.sy.chainproject.bean;

/**
 * @ data  2019/7/24 12:25
 * @ author  zxcg
 * VIP注册数据
 */
public class VIPRegisterData {

    /**
     * mkey : -1
     * userid : 1000
     * level : 2
     * phone : 15889555009
     * customerName : 张
     * Amount : 9000
     * remark :
     */

    private int mkey;
    private int userid;
    private int level;
    private String phone;
    private String customerName;
    private String Amount;
    private String remark;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
