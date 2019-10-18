package com.sy.chainproject.bean;

/**
 * @ data  2019/7/5 16:56
 * @ author  zxcg
 * 理由
 */
public class ReasonBean {


    /**
     * kind : 21
     * mkey : 52
     * dtime : null
     * stdid : 1
     * cnName : 无理由退货
     * enName : null
     */

    private int kind;
    private int mkey;
    private String dtime;
    private int stdid;
    private String cnName;
    private String enName;

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getMkey() {
        return mkey;
    }

    public void setMkey(int mkey) {
        this.mkey = mkey;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public int getStdid() {
        return stdid;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Object getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
