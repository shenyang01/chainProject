package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityCodeResultsBinding;

/**
 * @ data  2019/4/8 9:33
 * @ author  zxcg
 * 扫码结果
 */

public class CodeResultsActivity extends BaseActivity {
    private ActivityCodeResultsBinding binding;
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_code_results,null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding= (ActivityCodeResultsBinding) bindings;
        Intent intent = getIntent();
        if(intent==null)
            return;
        String code = intent.getStringExtra("result_code");
        binding.codeInfo.setText(code);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
