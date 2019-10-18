package com.sy.chainproject.bean;

import java.util.List;

/**
 * @ data  2019/5/27 11:25
 * @ author  zxcg
 * 收货修改提交
 */
public class ReceivingUpdateData {

    /**
     * storeid : 1905140904181000
     * size : 2
     * userid : 1000
     * "outStoreid": "1906020312571000",
     * "typeStore": "总部配货",
     * "dPerson": "陈先生",
     * remark :
     * data : [{"mkey":"19051409041810000050002","pid":"190514090418100000500","colorid":"005","sortid ":"5","qty":"9","fail ":"1"},{"mkey":"19051409041810000050003","pid":"190514090418100000500","colorid":"005","sortid ":"6","qty":"10","fail":"0"}]
     */
    private int size;
    private int userid;
    private String remark;
    private String outStoreid;
    private int typeStore;
    private String dPerson;
    private List<DataBean> data;

    public String getOutStoreid() {
        return outStoreid;
    }

    public void setOutStoreid(String outStoreid) {
        this.outStoreid = outStoreid;
    }

    public int getTypeStore() {
        return typeStore;
    }

    public void setTypeStore(int typeStore) {
        this.typeStore = typeStore;
    }

    public String getdPerson() {
        return dPerson;
    }

    public void setdPerson(String dPerson) {
        this.dPerson = dPerson;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mkey : 19051409041810000050002
         * pid : 190514090418100000500
         * colorid : 005
         * sortid  : 5
         * qty : 9
         * fail  : 1
         * fail : 0
         */

        private String mkey;
        private String pid;
        private String colorid;
        private String sortid;
        private String qty;
        private String fail;

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

        public String getSortid() {
            return sortid;
        }

        public void setSortid(String sortid) {
            this.sortid = sortid;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getFail() {
            return fail;
        }

        public void setFail(String fail) {
            this.fail = fail;
        }

    }
}
