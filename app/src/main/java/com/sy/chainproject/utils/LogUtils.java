package com.sy.chainproject.utils;

import android.util.Log;

/**
 * @ data  2018/8/13 17:22
 * @ author  zxcg
 * log 工具类
 */
public class LogUtils {
    private static boolean isBug = true;

    public static void e(String tag, String string) {
        if (isBug)
            Log.e(tag, string);
    }

    public static void v(String tag, String string) {
        if (isBug)
            Log.v(tag, string);
    }
    public static void v(String string) {
        if (isBug)
            Log.v("tag",string);
    }
    public static void e(String string) {
        if (isBug)
            Log.e("tag", string);
    }
}
