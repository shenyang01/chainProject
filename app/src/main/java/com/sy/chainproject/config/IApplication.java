package com.sy.chainproject.config;

import com.sy.chainproject.utils.SharedPreferencesUtils;


/**
 * @ data  2018/8/13 15:57
 * @ author  zxcg
 */
public class IApplication extends android.app.Application {
    private String TAG = "060_ZxcgApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        // AnomalyUtils.getInstance().init(this);
        SharedPreferencesUtils.init(this);
    }

}
