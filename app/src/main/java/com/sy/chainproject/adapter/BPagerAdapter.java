package com.sy.chainproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.github.chrisbanes.photoview.PhotoView;
import com.sy.chainproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2019/3/20 16:36
 * @ author  zxcg
 * viewPager 适配器
 */
public class BPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> list;
    private ImageView.ScaleType scaleType;



    public BPagerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
        list.add(R.mipmap.ssm);
    }
    /**
     * @param scaleType scaleType 图片缩放
     */
    public BPagerAdapter(Context context, ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
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
        ImageView imageView = new PhotoView(context);
        imageView.setTag(position % list.size());
        if (scaleType != null) imageView.setScaleType(scaleType);
        imageView.setImageResource(list.get(position % list.size()));
        container.addView(imageView);
        return imageView;
    }

}
