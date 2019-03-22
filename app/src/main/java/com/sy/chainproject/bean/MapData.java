package com.sy.chainproject.bean;


import java.io.Serializable;
import java.util.List;

/**
 * @ data  2018/8/21 15:32
 * @ author  zxcg
 */
public class MapData<T> implements Serializable {

    private static final long serialVersionUID = -1406696248528448601L;
    public PageInfo<T> pageInfo;
    public List<T> list;

}