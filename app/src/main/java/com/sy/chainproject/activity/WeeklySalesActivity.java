package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.ViewPagerAdapter;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityWeeklySalesBinding;
import com.sy.chainproject.fragment.HistogramFragment;
import com.sy.chainproject.fragment.TrendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2019/3/26 15:46
 * @ author  zxcg
 * 周图
 */
public class WeeklySalesActivity extends BaseActivity {
    private ActivityWeeklySalesBinding binding;
    private  List<Fragment> list;
    private List<String> list_Title;
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_weekly_sales, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityWeeklySalesBinding) bindings;
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getResources().getString(R.string.black));
        list = new ArrayList<>();
        list.add(new HistogramFragment());
        list.add(new TrendFragment());
        list_Title = new ArrayList<>();
        list_Title.add(getString(R.string.histogram));
        list_Title.add(getString(R.string.trend));
        binding.weeklyVp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),list,list_Title));
        binding.weeklyTab.setupWithViewPager(binding.weeklyVp);

    }
}
