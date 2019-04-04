package com.sy.chainproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sy.chainproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2019/3/20 16:36
 * @ author  zxcg
 */
public class BPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> list;
    public BPagerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(list.get(position%list.size()));
        container.addView(imageView);
        return imageView;
    }
    
}
