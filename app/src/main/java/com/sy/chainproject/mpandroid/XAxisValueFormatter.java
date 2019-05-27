package com.sy.chainproject.mpandroid;

import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 * @ data  2019/3/26 17:08
 * @ author  zxcg
 * mpa X 轴坐标
 */
public class XAxisValueFormatter extends ValueFormatter {
    private String[] xValues;
   // private int count=0;
    public XAxisValueFormatter(String[] xValues) {
        if (xValues == null) return;
        this.xValues = xValues;
    }

    @Override
    public String getFormattedValue(float value) {
        return xValues[(int) value];
    }
}
