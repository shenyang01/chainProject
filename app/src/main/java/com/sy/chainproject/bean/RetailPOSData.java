package com.sy.chainproject.bean;

import java.util.List;

/**
 * @ data  2019/7/1 10:30
 * @ author  zxcg
 * 零售提交接口
 */
public class RetailPOSData {


    /**
     * posid : 12231
     * posAmount : 2
     * totalAmount : 2
     * IMEI : 1000
     * flag : 0
     * currency : RMB
     * backStatus   1   0 为未退单   1为已退单
     * remark       退单原因 传索引
     * data : [{"posid":"12231","styleid":100091,"qty":1,"sellPrice":"100","discount":"10","subAmount":"6198","posAmount":"0","SellAttr":0,"shopid":"undefined","colorid":"005","sizeid":"13","plotid":"0","batch":1}]
     */

    private String posid;
    private String posAmount;
    private String totalAmount;
    private String userid;
    private int flag;
    private String currency;
    private List<DataBean> data;
    private int backStatus;
    private String remark;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(int backStatus) {
        this.backStatus = backStatus;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getPosAmount() {
        return posAmount;
    }

    public void setPosAmount(String posAmount) {
        this.posAmount = posAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * posid : 12231
         * styleid : 100091
         * qty : 1
         * sellPrice : 100
         * discount : 10
         * subAmount : 6198
         * posAmount : 0
         * SellAttr : 0
         * shopid : undefined
         * colorid : 005
         * sizeid : 13
         * plotid : 0
         * batch : 1
         */

        private String posid;
        private int styleid;
        private int qty;
        private String sellPrice;
        private String discount;
        private String subAmount;
        private String posAmount;
        private String shopid;
        private String colorid;
        private String sizeid;
        private String plotid;
        private int batch;

        public String getPosid() {
            return posid;
        }

        public void setPosid(String posid) {
            this.posid = posid;
        }

        public int getStyleid() {
            return styleid;
        }

        public void setStyleid(int styleid) {
            this.styleid = styleid;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(String sellPrice) {
            this.sellPrice = sellPrice;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getSubAmount() {
            return subAmount;
        }

        public void setSubAmount(String subAmount) {
            this.subAmount = subAmount;
        }

        public String getPosAmount() {
            return posAmount;
        }

        public void setPosAmount(String posAmount) {
            this.posAmount = posAmount;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getColorid() {
            return colorid;
        }

        public void setColorid(String colorid) {
            this.colorid = colorid;
        }

        public String getSizeid() {
            return sizeid;
        }

        public void setSizeid(String sizeid) {
            this.sizeid = sizeid;
        }

        public String getPlotid() {
            return plotid;
        }

        public void setPlotid(String plotid) {
            this.plotid = plotid;
        }

        public int getBatch() {
            return batch;
        }

        public void setBatch(int batch) {
            this.batch = batch;
        }
    }
}
