package com.sy.chainproject.mpandroid;


import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.sy.chainproject.R;

import java.util.Locale;

/**
 * @ data  2019/3/27 16:01
 * @ author  zxcg
 * 自定义顶部标签布局
 */
public class BMarkerView extends MarkerView {


    private TextView tv;
    private MPPointF mOffset;
    public BMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tv = findViewById(R.id.bar_marker);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tv.setText(String.format(Locale.getDefault(),"%.1f",e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        if(mOffset==null){
            mOffset=new MPPointF(-(getWidth()/2),-getWidth());
        }
        return mOffset;
    }
}
