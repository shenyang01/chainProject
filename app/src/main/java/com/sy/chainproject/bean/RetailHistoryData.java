package com.sy.chainproject.bean;

/**
 * @ data  2019/7/3 16:29
 * @ author  zxcg
 */
public class RetailHistoryData {

    /**
     * flag : 1
     * userid : 1000
     * startPage : 1
     * step : 30
     */

    private int flag;
    private String userid;
    private int startPage;
    private int step;
    private  String posid;

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
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
