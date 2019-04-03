package com.sy.chainproject.https;


import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2018/8/16 17:54
 * @ author  zxcg
 * 取消网络请求
 */
public class SubscriptionUtils {
    private List<Disposable> list = new ArrayList<>();

    static SubscriptionUtils getInstance() {
        return SingleTonHolder.singleTonInstance;
    }

    private static class SingleTonHolder {
        private static final SubscriptionUtils singleTonInstance = new SubscriptionUtils();
    }

    public void addSubscription(Disposable subscription) {
        list.add(subscription);
    }

    /**
     * 取消请求
     */
    public void cancel() {
        for (Disposable s : list) {
            s.dispose();
        }
        list.clear();
    }
}
