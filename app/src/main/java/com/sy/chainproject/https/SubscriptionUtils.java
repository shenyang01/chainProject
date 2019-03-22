package com.sy.chainproject.https;


import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2018/8/16 17:54
 * @ author  zxcg
 * 取消网络请求
 */
public class SubscriptionUtils {
    private List<Subscription> list = new ArrayList<>();

    public static SubscriptionUtils getInstance() {
        return SingleTonHolder.singleTonInstance;
    }

    private static class SingleTonHolder {
        private static final SubscriptionUtils singleTonInstance = new SubscriptionUtils();
    }

    public void addSubscription(Subscription subscription) {
        list.add(subscription);
    }

    /**
     * 取消请求
     */
    public void cancel() {
        for (Subscription s : list) {
            s.cancel();
        }
        list.clear();
    }
}
