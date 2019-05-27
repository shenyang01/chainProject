package com.sy.chainproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ data  2019/3/19 16:38
 * @ author  zxcg
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private List<String> list_Title;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> list_Title) {
        super(fm);
        this.list = list;
        this.list_Title = list_Title;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    /**
     * //此方法用来显示tab上的名字
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
