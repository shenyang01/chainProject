package com.sy.chainproject.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sy.chainproject.constant.Constants;

import java.util.ArrayList;
import java.util.List;


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

    public static  void setDataList(String tag, List<Integer> datalist) {
        SharedPreferences.Editor editor = sp.edit();
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.apply();
    }

    /**
     * 获取List
     */
    public static List<Integer> getDataList(String tag) {
        List<Integer> datalist = new ArrayList<Integer>();
        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<Integer>>() {
        }.getType());
        return datalist;

    }


}
