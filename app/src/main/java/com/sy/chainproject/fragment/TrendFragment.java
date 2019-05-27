package com.sy.chainproject.fragment;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseFragment;
import com.sy.chainproject.databinding.FragmentTrendBinding;
import com.sy.chainproject.mpandroid.XAxisValueFormatter;

import java.util.ArrayList;
import java.util.Locale;

/**
 * @ data  2019/4/8 16:27
 * @ author  zxcg
 * 趋势图
 */
public class TrendFragment extends BaseFragment {
    private FragmentTrendBinding binding;
    @Override
    public int getContent() {
        return R.layout.fragment_trend;
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (FragmentTrendBinding) bindings;
     binding.trendLinChart.setBorderWidth(getResources().getDimension(R.dimen.dp_20));
        binding.trendLinChart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);
        binding.trendLinChart.getDescription().setEnabled(false); //描述文字
        binding.trendLinChart.setPinchZoom(false);
        binding.trendLinChart.setDoubleTapToZoomEnabled(false);
        binding.trendLinChart.getDescription().setEnabled(false);
        Legend legend = binding.trendLinChart.getLegend();
        legend.setTextColor(Color.parseColor("#ffffff"));  //图例文字
        legend.setForm(Legend.LegendForm.NONE);   // 图例方块
        setTrendMap();
    }

    /**
     * 设置折线图
     */
    private void setTrendMap(){
        setHBarChartData();
        //设置X轴的刻度数
        String[] xValues = {"东城", "西城", "朝阳", "丰台", "石景山", "海淀区", "1111", "222", "333"};
       // binding.trendLinChart.setOnChartValueSelectedListener(this);

        // X Y 轴
        XAxis xAxis = binding.trendLinChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setDrawLabels(true);
        xAxis.setAxisLineWidth(1);
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(30f);//设置x轴标签的旋转角度
        xAxis.setDrawGridLines(false);

        ValueFormatter xAxisFormatter = new XAxisValueFormatter(xValues);
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis yAxis = binding.trendLinChart.getAxisLeft();
        yAxis.setAxisLineWidth(1);
        //yAxis.setSpaceTop(100);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(50);
    }
    /**
     * 设置水平柱形图数据的方法
     */
    private void setHBarChartData() {
        //填充数据，在这里换成自己的数据源
        ArrayList<Entry> yVals1 = new ArrayList<>();

        yVals1.add(new Entry(0, 1));
        yVals1.add(new Entry(1, 2));
        yVals1.add(new Entry(2, 3));
        yVals1.add(new Entry(3, 4));
        yVals1.add(new Entry(4, 5));
        yVals1.add(new Entry(5, 6));
        yVals1.add(new Entry(6, 7));
        yVals1.add(new Entry(7, 8));
        yVals1.add(new Entry(8, 9));

        LineDataSet set1;
        if (binding.trendLinChart.getData() != null && binding.trendLinChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) binding.trendLinChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            binding.trendLinChart.getData().notifyDataChanged();
            binding.trendLinChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(yVals1, "");
            set1.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return String.format(Locale.getDefault(), "%.1f", value);
                }
            });
            set1.setDrawIcons(false);
            set1.setColors(new int[]{R.color.coord_gray}, getActivity());   //图形颜色
            set1.setLineWidth(2f);//设置线宽
            set1.setCircleRadius(4f);//设置焦点圆心的大小
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData (dataSets);
            data.setValueTextSize(10f);
            binding.trendLinChart.setData(data);
        }
    }
}
