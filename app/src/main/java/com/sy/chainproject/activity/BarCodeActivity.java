package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityBarCodeBinding;

/**
 * @ data  2019/4/4 14:13
 * @ author  zxcg
 * 条码类
 */

public class BarCodeActivity extends BaseActivity {

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_bar_code, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        ActivityBarCodeBinding binding = (ActivityBarCodeBinding) bindings;
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.bar_tool));
        binding.barOne.setOnClickListener(this);
        binding.barQr.setOnClickListener(this);
        binding.barScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bar_scan:
                Intent intent = new Intent(BarCodeActivity.this, CaptureActivity.class);
                intent.putExtra("flag",Constants.FLAG);
                startActivity(intent);
                // intent = new Intent(BarCodeActivity.this, CodeResultsActivity.class);
                break;
            case R.id.bar_qr:
                intent = new Intent(BarCodeActivity.this, GenerateCodeActivity.class);
                intent.putExtra("code", 1);
                startActivity(intent);
                break;
            case R.id.bar_one:
                intent = new Intent(BarCodeActivity.this, GenerateCodeActivity.class);
                intent.putExtra("code", 0);
                startActivity(intent);
                break;
        }
    }
}
