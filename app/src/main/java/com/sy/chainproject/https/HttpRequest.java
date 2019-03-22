package com.sy.chainproject.https;

import com.sy.chainproject.bean.BaseData;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ data  2018/8/15 16:53
 * @ author  zxcg
 */
public class HttpRequest<T> {
    /**
     * @param flowable 添加线程参数
     */
    public Flowable<BaseData<T>> doHttpDeal(Flowable<BaseData<T>> flowable) {
        return flowable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
