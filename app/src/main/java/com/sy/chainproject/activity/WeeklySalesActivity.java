package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityWeeklySalesBinding;
import com.sy.chainproject.mpandroid.XAxisValueFormatter;

import java.util.ArrayList;
import java.util.Locale;

/**
 * @ data  2019/3/26 15:46
 * @ author  zxcg
 * 周图
 */
public class WeeklySalesActivity extends BaseActivity {
    private ActivityWeeklySalesBinding binding;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_weekly_sales, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityWeeklySalesBinding) bindings;
        setColor(getResources().getColor(R.color.bg_title_bar));
        binding.wsBarChart.setBorderWidth(getResources().getDimension(R.dimen.dp_20));
        binding.wsBarChart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);
        binding.wsBarChart.getDescription().setEnabled(false); //描述文字
        binding.wsBarChart.setPinchZoom(false);
        binding.wsBarChart.setDoubleTapToZoomEnabled(false);

        binding.wsBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
            }

            @Override
            public void onNothingSelected() {

            }
        });
        binding.wsBarChart.animateX(1000);

        //binding.wsBarChart.setMarker(new BMarkerView(this,R.layout.barchar_marker));

      Legend legend = binding.wsBarChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTextColor(Color.parseColor("#ffffff"));  //图例文字
        legend.setForm(Legend.LegendForm.NONE);   // 图例方块

        //设置X轴的刻度数
        String[] xValues = {"东城", "西城", "朝阳", "丰台", "石景山", "海淀区","1111","222","333"};

        // X Y 轴
        XAxis xAxis = binding.wsBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setLabelCount(xValues.length, false);
        xAxis.setDrawLabels(true);
        xAxis.setAxisLineWidth(1);
        xAxis.setDrawGridLines(false);
        ValueFormatter xAxisFormatter = new XAxisValueFormatter(xValues);
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis yAxis = binding.wsBarChart.getAxisLeft();
        yAxis.setAxisLineWidth(1);
        //yAxis.setSpaceTop(100);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(50);
        setHBarChartData();
    }

    /**
     * 设置水平柱形图数据的方法
     */
    private void setHBarChartData() {
        //填充数据，在这里换成自己的数据源
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        yVals1.add(new BarEntry(0, 1));
        yVals1.add(new BarEntry(1, 2));
        yVals1.add(new BarEntry(2, 3));
        yVals1.add(new BarEntry(3, 4));
        yVals1.add(new BarEntry(4, 5));
        yVals1.add(new BarEntry(5, 6));
        yVals1.add(new BarEntry(6, 7));
        yVals1.add(new BarEntry(7, 8));
        yVals1.add(new BarEntry(8, 9));
        BarDataSet set1;

        if (binding.wsBarChart.getData() != null && binding.wsBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) binding.wsBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            binding.wsBarChart.getData().notifyDataChanged();
            binding.wsBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "直方图");
            set1.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return String.format( Locale.getDefault(),"%.1f",value);
                }
            });
            set1.setDrawIcons(false);
            set1.setColors(new int[]{R.color.colorAccent},this);   //图形颜色
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.5f);
            binding.wsBarChart.setData(data);
        }

    }
}
