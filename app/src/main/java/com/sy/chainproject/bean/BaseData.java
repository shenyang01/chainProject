package com.sy.chainproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 回调信息统一封装类
 * Created by WZG on 2019/5/16.
 */
public class BaseData<E> implements Serializable {
    private static final long serialVersionUID = -1676011668592391622L;
    public List<E> data;  // 放置返回数据
    public int status;  //请求返回状态码 0  失败  1 成功
    public String message; // 请求返回信息  “执行成功 执行失败”
    public int messageCode;  //请求返回信息码
    public String clietToken;  //令牌
    public int size;  //数组长度

    public String ip;
    public String phone;
    public String telnum;
    public int level;
    public int shopid;
    public int areaid;
    public int userid;
    public String csphone;
    public String linkman;
    public String logourl;
    public String orgName;
    public String username;
    public String shortName;
    public String clientPort;
    public String databaseName;
}
