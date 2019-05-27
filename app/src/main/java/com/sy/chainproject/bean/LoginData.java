package com.sy.chainproject.bean;

import java.io.Serializable;

/**
 * @ data  2019/5/20 10:44
 * @ author  zxcg
 * 用户登录参数
 */
public class LoginData implements Serializable {
    private static final long serialVersionUID = -3738134959483612276L;
    private  String username;
    private  String passwd;
    private  String IMEI;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }
}
