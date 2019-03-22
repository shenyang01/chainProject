package com.sy.chainproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ data  2019/3/20 9:18
 * @ author  zxcg
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContent(),null);
        ViewDataBinding dataBinding = DataBindingUtil.bind(view);
        initView(dataBinding);
        return view;
    }

    public abstract int getContent();

    public abstract void initView(ViewDataBinding bindings);
}
