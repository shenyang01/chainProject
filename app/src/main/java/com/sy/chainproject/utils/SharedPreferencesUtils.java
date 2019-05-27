package com.sy.chainproject.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;


public class SharedPreferencesUtils {

    private static SharedPreferences sp;

    private static Context mContext;

    /**
     * @param context application 初始化
     */
    public static void init(Context context) {
        mContext = context;
        sp = mContext.getSharedPreferences(Constants.SPNAME, Context.MODE_PRIVATE);
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return sp.getString(key, "");
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }


    public static void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void setUserdata(String tag, UserBean userdata) {
        SharedPreferences.Editor editor = sp.edit();
        if (null == userdata) return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(userdata);
        editor.clear();
        editor.putString(tag, strJson);
        editor.apply();
    }

    /**
     * 获取List
     */
    public static UserBean getUserdata(String tag) {
        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(strJson, new TypeToken<UserBean>() {
        }.getType());
    }


}
