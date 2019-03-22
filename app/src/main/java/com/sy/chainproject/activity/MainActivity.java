package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityMainBinding;
import com.sy.chainproject.fragment.CoordinateFragment;
import com.sy.chainproject.fragment.HomeFragment;
import com.sy.chainproject.fragment.MeFragment;
import com.sy.chainproject.fragment.ProductFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private ActivityMainBinding binding;
    private int mindex;
    private FragmentTransaction transaction;
    private Fragment fragment[];
    private String tag[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.mainRG.setOnCheckedChangeListener(this);
        //不为null，说明是死而复活，移除已经存在的fragment
        if (savedInstanceState != null) {
            FragmentManager mManager = getSupportFragmentManager();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(mManager.findFragmentByTag(Constants.HOMEFRAGMENT));
            transaction.remove(mManager.findFragmentByTag(Constants.PRODUCTFRAGMENT));
            transaction.remove(mManager.findFragmentByTag(Constants.COORDINATEFRAGMENT));
            transaction.remove(mManager.findFragmentByTag(Constants.MEFRAGMENT));
            transaction.commit();
        }
    }

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_main, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityMainBinding) bindings;
        setColor(this, getResources().getColor(R.color.bg_title_bar));

        HomeFragment homeFragment = new HomeFragment();
        ProductFragment productFragment = new ProductFragment();
        CoordinateFragment coordinateFragment = new CoordinateFragment();
        MeFragment meFragment = new MeFragment();
        tag = new String[]{Constants.HOMEFRAGMENT, Constants.PRODUCTFRAGMENT, Constants.COORDINATEFRAGMENT, Constants.MEFRAGMENT};
        fragment = new Fragment[]{homeFragment, productFragment, coordinateFragment, meFragment};
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragment, fragment[0], tag[0]).commit();
    }

    private void showFragment(int index) {
        transaction = getSupportFragmentManager().beginTransaction();
        if (this.mindex == index) return;
        transaction.hide(fragment[mindex]);
        if (!fragment[index].isAdded()) {
            transaction.add(R.id.main_fragment, fragment[index], tag[index]).show(fragment[index]);
        } else {
            transaction.show(fragment[index]);
        }
        transaction.commit();
        mindex = index;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_home:
                showFragment(0);
                setBaseTitle(getResources().getString(R.string.home));
                break;
            case R.id.main_product:
                showFragment(1);
                setBaseTitle(getResources().getString(R.string.product));
                break;
            case R.id.main_coordinate:
                showFragment(2);
                setBaseTitle(getResources().getString(R.string.coordinate));
                break;
            case R.id.main_me:
                showFragment(3);
                setBaseVisibility(R.id.base_rl);
                break;
        }
    }
    @Override
    public void onClick(View v) {
       // super.onClick(v);
    }
}
