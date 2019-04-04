package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityBarCodeBinding;

/**
 * @ data  2019/4/4 14:13
 * @ author  zxcg
 * 条码类
 */

public class BarCodeActivity extends BaseActivity {
    private ActivityBarCodeBinding binding;
    private Intent intent;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_bar_code, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityBarCodeBinding) bindings;
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.bar_tool));

        binding.barOne.setOnClickListener(this);
        binding.barQr.setOnClickListener(this);
        binding.barScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bar_scan:
                intent = new Intent(BarCodeActivity.this, CaptureActivity.class);
                break;
            case R.id.bar_qr:
                break;
            case R.id.bar_one:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
