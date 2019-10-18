package com.sy.chainproject.bean;

/**
 * @ data  2019/7/29 10:22
 * @ author  zxcg
 * 调拨
 */
public class TransshipmentData {

    /**
     * userid : 1000
     * D1 : 2019-4-1
     * D2 : 2019-7-26
     * d1   商品明细
     * d2
     * storeNum :
     * styleNo :
     * styleName :
     * seasonName :
     * brandName :
     */

    private int userid;
    private String D1;
    private String D2;
    private String storeNum;
    private String styleNo;
    private String styleName;
    private int seasonName;
    private int brandName;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getStyleNo() {
        return styleNo;
    }

    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public int getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(int seasonName) {
        this.seasonName = seasonName;
    }

    public int getBrandName() {
        return brandName;
    }

    public void setBrandName(int brandName) {
        this.brandName = brandName;
    }
}
