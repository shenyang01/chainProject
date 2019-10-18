package com.sy.chainproject.bean;

/**
 * @ data  2019/7/23 16:45
 * @ author  zxcg
 * VIP 数据上传
 */
public class VIPData {

    /**
     * userid : 1000
     * phone :
     * D1 : 2019-4-1
     * D2 : 2019-7-22
     */

    private int userid;
    private String phone;
    private String D1;
    private String D2;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getD1() {
        return D1;
    }

    public void setD1(String D1) {
        this.D1 = D1;
    }

    public String getD2() {
        return D2;
    }

    public void setD2(String D2) {
        this.D2 = D2;
    }
}
