package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityForgetBinding;

/**
 * @ data  2019/4/2 14:21
 * @ author  zxcg
 * 忘记密码
 */
public class ForgetActivity extends BaseActivity {
    private ActivityForgetBinding binding;
    private long currentTime;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_forget, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityForgetBinding) bindings;
        setColor(getResources().getColor(R.color.white));
        setBaseVisibility(R.id.base_rl);
        binding.forgetSend.setOnClickListener(this);
        binding.forgetBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_bt:
                forget();
                break;
            case R.id.forget_send:
                break;
        }
        finish();
    }

    /**
     * 修改提交
     */
    private void forget() {
        String pws = binding.forgetPws.getEditText().getText().toString();
        String pws2 = binding.forgetPws2.getEditText().getText().toString();
        String code = binding.forgetCode.getEditText().getText().toString();
        if (TextUtils.isEmpty(pws)) {
            showToast("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(pws) || TextUtils.isEmpty(pws2)) {
            showToast("密码不能为空");
            return;
        }
        if (!pws.equals(pws2)) {
            showToast("两次输入的密码不一致");
            return;
        }
        startActivity(new Intent(ForgetActivity.this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentTime < 1000) {
            finish();
        }
        currentTime = System.currentTimeMillis();
        //super.onBackPressed();
    }
}
