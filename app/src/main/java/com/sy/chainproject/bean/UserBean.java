package com.sy.chainproject.bean;

import java.io.Serializable;

/**
 * @ data  2019/5/20 10:49
 * @ author  zxcg
 * 登录返回的用户信息
 */
public class UserBean implements Serializable {
    private static final long serialVersionUID = -8951072022307418638L;

    /**
     * ip : http://127.0.0.1:5000
     * level : 0
     * orgid : 0
     * phone : 13662594417
     * areaid : 12
     * telnum : 0755-26854581
     * userid : 1000
     * csphone : 0755-26854581
     * linkman : 何先生
     * logourl : /bmcp/logopng/logo_pite.png
     * orgName : 深圳开发科技有限公司
     * succeed : 0
     * username : Admin
     * shortName : 发科技
     * clientPort : 5000
     * clietToken :
     * databaseName : SHOPDB0001
     */

    private String ip;
    private int level;
    private int shopid;
    private String phone;
    private int areaid;
    private String telnum;
    private int userid;
    private String csphone;
    private String linkman;
    private String logourl;
    private String orgName;
    private int succeed;
    private String username;
    private String shortName;
    private String clientPort;
    private String clietToken;
    private String databaseName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAreaid() {
        return areaid;
    }

    public void setAreaid(int areaid) {
        this.areaid = areaid;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCsphone() {
        return csphone;
    }

    public void setCsphone(String csphone) {
        this.csphone = csphone;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getClietToken() {
        return clietToken;
    }

    public void setClietToken(String clietToken) {
        this.clietToken = clietToken;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
