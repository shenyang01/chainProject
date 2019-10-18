package com.sy.chainproject.bean;

/**
 * @ data  2019/5/27 10:39
 * @ author  zxcg
 * 查询
 */
public class QueryData {
    /**
     * userid : 1000
     * databaseName : SHOPDB0001
     * styleNo : 8
     * styleName :
     * startPage : 0
     * step : 30
     * flag: 1
     * shopid 查询库存是使用   0 查询所有门店   大于0  查询当前门店
     */

    private String userid;
    private String databaseName;
    private String styleNo;
    private String styleName;
    private int startPage;
    private int step;
    private int flag;
    private int shopid;

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
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

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
