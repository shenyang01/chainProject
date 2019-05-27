package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;

/**
 * @ data  2019/5/8 16:49
 * @ author  zxcg
 * 退货页面
 */
public class ReturnGoodsActivity extends BaseActivity {

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_return_goods, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {

    }
}
