package com.sy.chainproject.bean;

import java.io.Serializable;

/**
 * 回调信息统一封装类
 * Created by WZG on 2016/7/16.
 */
public class BaseData<T> implements Serializable {
    private static final long serialVersionUID = -1676011668592391622L;
    public MapData<T> sysdata;
    public String status;
    public String message;
    public String messageCode;
    public String index;

}
