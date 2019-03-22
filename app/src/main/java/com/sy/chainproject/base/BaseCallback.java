package com.sy.chainproject.base;

/**
 * @ data  2018/8/14 14:45
 * @ author  zxcg
 * 结果回调类
 */
public interface BaseCallback<T> {
    void onSucceed(T t);

    void onFailure(String err);
}