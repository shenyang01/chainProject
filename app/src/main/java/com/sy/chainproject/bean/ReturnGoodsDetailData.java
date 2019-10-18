package com.sy.chainproject.bean;

import java.util.List;

/**
 * @ data  2019/8/8 11:51
 * @ author  zxcg
 * 退货及修改数据
 */
public class ReturnGoodsDetailData {

    /**
     * storeid : 0
     * userid : 1000
     * storeNum :
     * pAmount : 0
     * currency : RMB
     * dyDate : 2019-8-6
     * seasonid : 12
     * brandid : 16
     * remark :
     * data : [{"pid":"","batch":1,"storeid":0,"styleid":100091,"colorid":"005","buyPrice":"","sizeCode":"A2","des":"","buyUnit":"1","qty":"-5","sortid":2,"stockid":"100091005021"},{"pid":"","batch":1,"storeid":0,"styleid":100091,"colorid":"005","buyPrice":"","sizeCode":"A2","des":"","buyUnit":"1","qty":"-5","sortid":3,"stockid":"100091005031"},{"pid":"","batch":1,"storeid":0,"styleid":100091,"colorid":"005","buyPrice":"","sizeCode":"A2","des":"","buyUnit":"1","qty":"-5","sortid":4,"stockid":"100091005041"},{"pid":"","batch":1,"storeid":0,"styleid":100091,"colorid":"005","buyPrice":"","sizeCode":"A2","des":"","buyUnit":"1","qty":"-5","sortid":5,"stockid":"100091005051"},{"pid":"","batch":1,"storeid":0,"styleid":100091,"colorid":"005","buyPrice":"","sizeCode":"A2","des":"","buyUnit":"1","qty":"-5","sortid":6,"stockid":"100091005061"}]
     */

    private String storeid;
    private int userid;
    private String storeNum;
    private String pAmount;
    private String currency;
    private String dyDate;
    private String seasonid;
    private String brandid;
    private String remark;
    private List<DataBean> data;

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getPAmount() {
        return pAmount;
    }

    public void setPAmount(String pAmount) {
        this.pAmount = pAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDyDate() {
        return dyDate;
    }

    public void setDyDate(String dyDate) {
        this.dyDate = dyDate;
    }

    public String getSeasonid() {
        return seasonid;
    }

    public void setSeasonid(String seasonid) {
        this.seasonid = seasonid;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
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
         * pid :
         * batch : 1
         * storeid : 0
         * styleid : 100091
         * colorid : 005
         * buyPrice :
         * sizeCode : A2
         * des :
         * buyUnit : 1
         * qty : -5
         * sortid : 2
         * stockid : 100091005021
         */

        private String pid;
        private int batch;
        private String storeid;
        private String styleid;
        private String colorid;
        private String buyPrice;
        private String sizeCode;
        private String des;
        private String buyUnit;
        private String qty;
        private String sortid;
        private String stockid;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getBatch() {
            return batch;
        }

        public void setBatch(int batch) {
            this.batch = batch;
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

        public String getColorid() {
            return colorid;
        }

        public void setColorid(String colorid) {
            this.colorid = colorid;
        }

        public String getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(String buyPrice) {
            this.buyPrice = buyPrice;
        }

        public String getSizeCode() {
            return sizeCode;
        }

        public void setSizeCode(String sizeCode) {
            this.sizeCode = sizeCode;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getBuyUnit() {
            return buyUnit;
        }

        public void setBuyUnit(String buyUnit) {
            this.buyUnit = buyUnit;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getSortid() {
            return sortid;
        }

        public void setSortid(String sortid) {
            this.sortid = sortid;
        }

        public String getStockid() {
            return stockid;
        }

        public void setStockid(String stockid) {
            this.stockid = stockid;
        }
    }
}
