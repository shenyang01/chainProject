package com.sy.chainproject.bean;

/**
 * @ data  2019/8/7 16:20
 * @ author  zxcg
 * 退货明细
 */
public class ReturnGoodsDetailBean {

    /**
     * storeid : 1907260329401000
     * styleid : 100091
     * mkey : 19072603294010000052
     * pid : 1907260329401000005
     * colorid : 005
     * colorName : 粉色
     * sizeid : 12
     * sortid : 2
     * sizeName : S
     * qty : 1400
     * styleName : 连衣裙
     * unitName : 件
     * sizeCode
     * state  状态  判断是否已确认修改
     */

    private String storeid;
    private String styleid;
    private String mkey;
    private String pid;
    private String colorid;
    private String colorName;
    private String sizeid;
    private String sortid;
    private String sizeName;
    private int qty;
    private String styleName;
    private String unitName;
    private String sizeCode;
    private boolean state = true;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
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

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
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

    public String getSortid() {
        return sortid;
    }

    public void setSortid(String sortid) {
        this.sortid = sortid;
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

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
