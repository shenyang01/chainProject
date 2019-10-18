package com.sy.chainproject.https;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ data  2018/8/15 16:53
 * @ author  zxcg
 * 定制格式 针对非 BaseData<T> 格式
 */
public class HttpRequestOther<T> {
    /**
     * @param observable 添加线程参数
     */
    public Observable<T> doHttpDeal(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}