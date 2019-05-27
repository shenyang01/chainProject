package com.sy.chainproject.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sy.chainproject.R;
import com.sy.chainproject.activity.ImageViewPagerActivity;
import com.sy.chainproject.adapter.BPagerAdapter;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseFragment;
import com.sy.chainproject.databinding.FragmentHomeBinding;
import com.sy.chainproject.handler.BaseHandler;
import com.sy.chainproject.view.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2019/3/20 9:25
 * @ author  zxcg
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener, BaseHandler.OnReceiveMessageListener, BaseViewHolder.ViewOnclick, RefreshRecyclerView.OnPullRefresh{
    private FragmentHomeBinding binding;
    private List<View> dotlist;
    private long currentTime = 0;
    private int currentItem = 0;
    private BaseHandler.HandlerHolder handler = new BaseHandler.HandlerHolder(this);

    @Override
    public int getContent() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (FragmentHomeBinding) bindings;
        BPagerAdapter adapter = new BPagerAdapter(getActivity(), ImageView.ScaleType.FIT_XY);
        binding.homeViewPager.setAdapter(adapter);
        binding.homeViewPager.addOnPageChangeListener(this);
        dotlist = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i + "测试数据，可以点击..");
            // 添加点
            View view = new View(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.leftMargin = 10;
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.dot_normal);
            dotlist.add(view);
            binding.homeSpot.addView(view);
        }
        binding.homeViewPager.setCurrentItem((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % dotlist.size());

        binding.homeRv.setIsData(false);
        binding.homeRv.setOnPullRefresh(this);
        binding.homeRv.setRecyclerViewAdapter(new BaseAdapter<String>(getActivity(), R.layout.rv_item_home, list, true, this) {
            @Override
            public void convert(BaseViewHolder holder, String data, int position) {
                holder.setOnclick(R.id.home_rv_tv, position);
                holder.setText(R.id.home_rv_tv, data);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(0, 4000);
    }

    @Override
    public void handlerMessage(Message msg) {
        if (System.currentTimeMillis() - currentTime < 1000) return;
        binding.homeViewPager.setCurrentItem(currentItem + 1);
        handler.sendEmptyMessageDelayed(0, 4000);
        currentTime = System.currentTimeMillis();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 当前选择的页面
        currentItem = position;
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
    public void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void clickView(View v, int position) {
        startActivity(new Intent(getActivity(), ImageViewPagerActivity.class));
    }

    @Override
    public void PullRefresh() {

    }

    @Override
    public void onDestroyView() {
        dotlist.clear();
        dotlist=null;
        super.onDestroyView();
    }
}
