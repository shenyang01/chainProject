package com.sy.chainproject.bean;

/**
 * @ data  2019/7/23 16:42
 * @ author  zxcg
 * VIP  返回数据
 */
public class VIPBean {

    /**
     * customerName : 0
     *  levelName : 金卡
     *  totalIntegral : 3333
     * Amount : 200
     * shopName : “总店”
     * phone : 15889555003
     * dtime : 2019-07-10 20:48:09.0
     */

    private String customerName;
    private String levelName;
    private int totalIntegral;
    private int Amount;
    private String shopName;
    private String phone;
    private String dtime;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }
}
