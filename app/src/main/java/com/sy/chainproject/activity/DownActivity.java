package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;

/**
 * @ data  2019/3/22 14:55
 * @ author  zxcg
 * 二维码下载界面
 */
public class DownActivity extends BaseActivity {
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_down,null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(this,getResources().getColor(R.color.bg_title_bar));
    }
}
