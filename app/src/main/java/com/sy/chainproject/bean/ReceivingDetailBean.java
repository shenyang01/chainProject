package com.sy.chainproject.bean;

/**
 * @ data  2019/5/21 15:40
 * @ author  zxcg
 * 收货详情
 */
public class ReceivingDetailBean {

    /**
     * storeid : 1905140904181000
     * styleid : 100091
     * pid : 190514090418100000500
     * colorid : 005
     * colorName : 粉色
     * sizeid : 11
     * sizeName : XS
     * qty : 4
     * sortid  尺码序号
     * qty_no  次品
     */

    private String storeid;
    private String styleid;
    private String pid;
    private String mkey;
    private String colorid;
    private String colorName;
    private String sizeid;
    private String sortid;
    private String sizeName;
    private int qty;
    private String qty_no;

    public String getQty_no() {
        return qty_no;
    }

    public void setQty_no(String qty_no) {
        this.qty_no = qty_no;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getSortid() {
        return sortid;
    }

    public void setSortid(String sortid) {
        this.sortid = sortid;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getStyleid() {
        return styleid;
    }

    public void setStyleid(String styleid) {
        this.styleid = styleid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeid() {
        return sizeid;
    }

    public void setSizeid(String sizeid) {
        this.sizeid = sizeid;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
