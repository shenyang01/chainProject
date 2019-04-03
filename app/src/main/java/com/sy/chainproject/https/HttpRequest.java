package com.sy.chainproject.https;

import com.sy.chainproject.bean.BaseData;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ data  2018/8/15 16:53
 * @ author  zxcg
 */
public class HttpRequest<T> {
    /**
     * @param observable 添加线程参数
     */
    public Observable<BaseData<T>> doHttpDeal(Observable<BaseData<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
