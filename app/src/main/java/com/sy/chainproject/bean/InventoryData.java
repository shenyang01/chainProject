package com.sy.chainproject.bean;

/**
 * @ data  2019/7/10 12:24
 * @ author  zxcg
 * 盘点
 */
public class InventoryData {

    /**
     * mkid : 1907090959001000
     * dyear : 2019
     * dmonth : 4
     * userid : 1000
     * typeid: 0 为盘点   1 为月报
     * qty :  ""
     * remark :
     * <p>
     * mkey  0
     */

    private String mkid;
    private String dyear;
    private String dmonth;
    private int userid;
    private String remark;
    private String qty;
    private int mkey;
    private int typeid;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getMkey() {
        return mkey;
    }

    public void setMkey(int mkey) {
        this.mkey = mkey;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getMkid() {
        return mkid;
    }

    public void setMkid(String mkid) {
        this.mkid = mkid;
    }

    public String getDyear() {
        return dyear;
    }

    public void setDyear(String dyear) {
        this.dyear = dyear;
    }

    public String getDmonth() {
        return dmonth;
    }

    public void setDmonth(String dmonth) {
        this.dmonth = dmonth;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
