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
     */

    private String userid;
    private String databaseName;
    private String styleNo;
    private String styleName;
    private int startPage;
    private int step;

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
