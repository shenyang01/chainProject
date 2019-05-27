package com.sy.chainproject.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @ data  2019/4/8 14:20
 * @ author  zxcg
 */

public class TypedValueUtils {
    public static int dpTopx(Context context,float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,
                context.getApplicationContext().getResources().getDisplayMetrics());
    }

}
