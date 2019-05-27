package com.sy.chainproject.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BPagerAdapter;
import com.sy.chainproject.databinding.ActivityImageViewpagerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2019/4/10 11:59
 * @ author  zxcg
 * 商品详情
 */

public class ImageViewPagerActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ActivityImageViewpagerBinding binding;
    private List<View> dotlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_viewpager);
        //全屏
        initView();
    }

    public void initView() {
        binding.detailsViewPager.setAdapter(new BPagerAdapter(this));
        binding.detailsViewPager.addOnPageChangeListener(this);
        dotlist = new ArrayList<>();
        setDot();
    }

    /**
     * 添加小圆点
     */
    private void setDot() {
        for (int i = 0; i < 5; i++) {
            // 添加点
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.leftMargin = 10;
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.dot_normal);
            dotlist.add(view);
            binding.homeSpot.addView(view);
        }
        dotlist.get(0).setBackgroundResource(R.drawable.dot_focused);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 当前选择的页面
        // 设置小圆点背景
        for (int i = 0; i < dotlist.size(); i++) {
            if (i == position % dotlist.size()) dotlist.get(i).setBackgroundResource(R.drawable.dot_focused);
            else dotlist.get(i).setBackgroundResource(R.drawable.dot_normal);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        dotlist.clear();
        dotlist=null;
        super.onDestroy();
    }
}
