package com.sy.chainproject.bean;

/**
 * @ data  2019/5/16 16:52
 * @ author  zxcg
 * 收货订单列表
 */
public class ReceivingNoteBean {

    /**
     * mkey : 190514090418100000500
     * dtime : 2019-05-14 09:04
     * detail : [{"pid":"190514090418100000500","qty":4,"mkey":"","sizeid":11,"colorid":"005","storeid":"1905140904181000","styleid":100091,"sizeName":"XS","colorName":"粉色"},{"pid":"190514090418100000500","qty":4,"mkey":"","sizeid":12,"colorid":"005","storeid":"1905140904181000","styleid":100091,"sizeName":"S","colorName":"粉色"},{"pid":"190514090418100000500","qty":4,"mkey":"","sizeid":13,"colorid":"005","storeid":"1905140904181000","styleid":100091,"sizeName":"M","colorName":"粉色"},{"pid":"190514090418100000500","qty":4,"mkey":"","sizeid":14,"colorid":"005","storeid":"1905140904181000","styleid":100091,"sizeName":"L","colorName":"粉色"},{"pid":"190514090418100000500","qty":4,"mkey":"","sizeid":15,"colorid":"005","storeid":"1905140904181000","styleid":100091,"sizeName":"XL","colorName":"粉色"},{"pid":"190514090418100000500","qty":4,"mkey":"","sizeid":16,"colorid":"005","storeid":"1905140904181000","styleid":100091,"sizeName":"XXL","colorName":"粉色"}]
     * remark :
     * pAmount : 0
     * storeid : 1905140904181000
     * styleNo : SK8002
     * styleid : 100091
     * typeOut : 总部配货
     * currency : RMB
     * discount : 10.0
     * storeNum : OC19050001
     * totalQty : 24
     * typeName : 女式T恤
     * unitName : 件
     * userName : Admin
     * actAmount : 0.0
     * imageUrl1 : http://127.0.0.1:5000/pic/5000/100091_1.png?2019051616
     * imageUrl2 : http://127.0.0.1:5000/pic/5000/100091_2.png?2019051616
     * imageUrl3 : http://127.0.0.1:5000/pic/5000/100091_3.png?2019051616
     * imageUrl4 : http://127.0.0.1:5000/pic/5000/100091_4.png?2019051616
     * imageUrl5 : http://127.0.0.1:5000/pic/5000/100091_5.png?2019051616
     * sellPrice : 0.0
     * styleName : 连衣裙
     * customerid : 10002
     * totalAmount : 0
     * dtime
     */

    private String mkey;
    private String dtime;
    private String remark;
    private String pAmount;
    private long storeid;
    private String styleNo;
    private int styleid;
    private String typeOut;
    private String currency;
    private String discount;
    private String storeNum;
    private int totalQty;
    private String typeName;
    private String unitName;
    private String userName;
    private String actAmount;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;
    private String imageUrl5;
    private String sellPrice;
    private String styleName;
    private int customerid;
    private String totalAmount;

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPAmount() {
        return pAmount;
    }

    public void setPAmount(String pAmount) {
        this.pAmount = pAmount;
    }

    public long getStoreid() {
        return storeid;
    }

    public void setStoreid(long storeid) {
        this.storeid = storeid;
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

    public String getTypeOut() {
        return typeOut;
    }

    public void setTypeOut(String typeOut) {
        this.typeOut = typeOut;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActAmount() {
        return actAmount;
    }

    public void setActAmount(String actAmount) {
        this.actAmount = actAmount;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }


}
