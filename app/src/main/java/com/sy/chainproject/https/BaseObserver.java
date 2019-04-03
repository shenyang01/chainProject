package com.sy.chainproject.https;

import android.accounts.NetworkErrorException;
import android.util.Log;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * @ company zxcg
 * @ name sy
 */
public abstract class BaseObserver<T> implements Observer<T> {

    public void onSubscribe(Disposable s) {
        SubscriptionUtils.getInstance().addSubscription(s);
    }

    @Override
    public void onNext(T t) {
        try {
            Log.e("tag", Thread.currentThread().toString());
            onSucceed(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("tag", "e.getMessage()  " + e.getMessage());
        try {
            if (e instanceof ConnectException || e instanceof TimeoutException || e instanceof UnknownHostException) {
                onFailure("连接超时,请检查网络状态");
            } else if (e instanceof SocketTimeoutException) { //接口错误
                onFailure("请求异常");
            } else if (e instanceof NetworkErrorException) {
                onFailure("无网络");
            } else {
                onFailure("未知错误");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     */
    public abstract void onSucceed(T t);


    /**
     * 返回失败
     *
     * @ param  错误原因
     */
    public abstract void onFailure(String e);

}
