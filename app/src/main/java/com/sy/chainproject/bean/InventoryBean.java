package com.sy.chainproject.bean;

/**
 * @ data  2019/7/10 12:11
 * @ author  zxcg
 * 盘点单数据
 */
public class InventoryBean {

    /**
     * qty : -6
     * mkey : 66
     * mkid : 1907091017061000
     * batch : 1
     * Amount : -6042
     * sizeid : 12
     * barCode : 900011000910050201
     * bookQty : -6
     * colorid : 005
     * stockid : 1000910050201
     * avgPrice : 1007
     * styleNo : SK8002
     * styleid : 100091
     * sizeName : S
     * unitName : 件
     * colorName : 粉色
     * styleName : 连衣裙
     * deficitQty : 0
     */

    private int qty;
    private int mkey;
    private long mkid;
    private int batch;
    private int Amount;
    private String sizeid;
    private String barCode;
    private int bookQty;
    private String colorid;
    private String stockid;
    private String avgPrice;
    private String styleNo;
    private int styleid;
    private String sizeName;
    private String unitName;
    private String colorName;
    private String styleName;
    private int deficitQty;
    private String state ="确定";   // 提交 修改状态

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getMkey() {
        return mkey;
    }

    public void setMkey(int mkey) {
        this.mkey = mkey;
    }

    public long getMkid() {
        return mkid;
    }

    public void setMkid(long mkid) {
        this.mkid = mkid;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getSizeid() {
        return sizeid;
    }

    public void setSizeid(String sizeid) {
        this.sizeid = sizeid;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getBookQty() {
        return bookQty;
    }

    public void setBookQty(int bookQty) {
        this.bookQty = bookQty;
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getStyleNo() {
        return styleNo;
    }

    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }

    public int getStyleid() {
        return styleid;
    }

    public void setStyleid(int styleid) {
        this.styleid = styleid;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public int getDeficitQty() {
        return deficitQty;
    }

    public void setDeficitQty(int deficitQty) {
        this.deficitQty = deficitQty;
    }
}
