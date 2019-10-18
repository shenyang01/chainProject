package com.sy.chainproject.bean;

/**
 * @ data  2019/7/16 16:30
 * @ author  zxcg
 * 盘点 月报历史数据
 */
public class HistoryData {

    /**
     * userid : 1000
     * typeid : 1
     * startPage : 1
     * step : 100
     */

    private int userid;
    private int typeid;
    private int startPage;
    private int step;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
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
