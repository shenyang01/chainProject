package com.sy.chainproject.bean;

import java.io.Serializable;

/**
 * @ data  2019/6/6 10:26
 * @ author  zxcg
 * 门店库存接口与POS 零售明细接口  JSON 格式一致
 */
public class RetailBean implements Serializable {

    private static final long serialVersionUID = 4533676803776537885L;
    /**
     * stockid : 1000910050201
     * plotStatus : 0
     * dtime : 2019-06-02 08:42
     * colorid : 005
     * colorName : 粉色
     * sizeName : S
     * sellPrice : 100
     * qty : -1
     * barCode : 900011000910050201
     * styleNo : SK8002
     * styleName : 连衣裙
     * plotWay :
     * shopid : 0
     * plotid : 0
     * plottypeid : 0
     * batch : 1
     * discount : 10
     * subAmount : 0
     * posAmount : 0
     * styleid : 100091
     * messageCode : 200
     * message :
     * startPage : 1
     * step : 30
     * userid : 0
     * IMEI : 123321
     * stdUnit : 1
     * shopName
     * sizeCode
     * sortid
     * seasonid
     * brandid
     *   * state  状态  判断是否已确认修改
     */

    private String stockid;
    private int plotStatus;
    private String dtime;
    private String colorid;
    private String colorName;
    private String sizeName;
    private String sellPrice;
    private int qty;
    private String barCode;
    private String styleNo;
    private String styleName;
    private String plotWay;
    private int shopid;
    private int plotid;
    private int plottypeid;
    private int batch;
    private String discount;
    private String subAmount;
    private String posAmount;
    private int styleid;
    private int messageCode;
    private String message;
    private int startPage;
    private int step;
    private int userid;
    private String IMEI;
    private int stdUnit;
    private String brandName;
    private String SellAttr;
    private String shopName;
    private String sizeCode;
    private int sizeid;

    private int sortid;
    private int seasonid;
    private int brandid;
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getSortid() {
        return sortid;
    }

    public void setSortid(int sortid) {
        this.sortid = sortid;
    }

    public int getSeasonid() {
        return seasonid;
    }

    public void setSeasonid(int seasonid) {
        this.seasonid = seasonid;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSellAttr() {
        return SellAttr;
    }

    public void setSellAttr(String sellAttr) {
        SellAttr = sellAttr;
    }

    public int getSizeid() {
        return sizeid;
    }

    public void setSizeid(int sizeid) {
        this.sizeid = sizeid;
    }

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    public int getPlotStatus() {
        return plotStatus;
    }

    public void setPlotStatus(int plotStatus) {
        this.plotStatus = plotStatus;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
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

    public String getPlotWay() {
        return plotWay;
    }

    public void setPlotWay(String plotWay) {
        this.plotWay = plotWay;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getPlotid() {
        return plotid;
    }

    public void setPlotid(int plotid) {
        this.plotid = plotid;
    }

    public int getPlottypeid() {
        return plottypeid;
    }

    public void setPlottypeid(int plottypeid) {
        this.plottypeid = plottypeid;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
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

    public int getStyleid() {
        return styleid;
    }

    public void setStyleid(int styleid) {
        this.styleid = styleid;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public int getStdUnit() {
        return stdUnit;
    }

    public void setStdUnit(int stdUnit) {
        this.stdUnit = stdUnit;
    }
}
