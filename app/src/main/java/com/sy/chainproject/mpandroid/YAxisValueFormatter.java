package com.sy.chainproject.mpandroid;

import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 * @ data  2019/3/26 17:12
 * @ author  zxcg
 */
public class YAxisValueFormatter extends ValueFormatter {
    private String[] yValues;
    public YAxisValueFormatter(String[] yValues) {
        if (yValues == null) return;
        this.yValues = yValues;
    }

    @Override
    public String getFormattedValue(float value) {
        return yValues[(int) value];
    }
}
