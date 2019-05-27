package com.sy.chainproject.bean;

/**
 * @ data  2019/5/27 10:48
 * @ author  zxcg
 * 查询返回数据
 */
public class QueryBean {

    /**
     * qty : 10
     *  stockid  : 0
     * Amount : 1220
     * avgPrice : 0
     * barCode : 900011000000020801
     * styleNo : 8898A下A
     * styleName : efwfessf
     * colorName : 红色
     * sizeName : 2Xl
     * shopName : 东门店
     */

    private String qty;
    private String stockid;
    private String Amount;
    private String avgPrice;
    private String barCode;
    private String styleNo;
    private String styleName;
    private String colorName;
    private String sizeName;
    private String shopName;

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
