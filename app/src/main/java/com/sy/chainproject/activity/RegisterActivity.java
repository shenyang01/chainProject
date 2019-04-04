package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityRegisterBinding;
import com.sy.chainproject.utils.SharedPreferencesUtils;


/**
 * @ data  2019/4/1 16:02
 * @ author  zxcg
 * 用户注册
 */
public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding binding;
    private long currentTime;
    private String code; //验证码

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_register, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityRegisterBinding) bindings;
        setColor(getResources().getColor(R.color.white));
        setBaseVisibility(R.id.base_rl);
        binding.registerCode.setOnClickListener(this);
        binding.registerSend.setOnClickListener(this);
        binding.registerLogin.setOnClickListener(this);
        binding.registerBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.register_send:
                break;
            case R.id.register_bt:
                register();
                break;
        }

    }

    /**
     * 发送短信验证码
     */
    private void registerSend() {

    }

    /**
     * 注册
     */
    private void register() {
        code = binding.registerCode.getEditText().getText().toString();
        if (TextUtils.isEmpty(code)) {
            showToast("验证码不能为空");
            return;
        }
        String phone = binding.registerPhone.getEditText().getText().toString();
        String pws = binding.registerPws.getEditText().getText().toString();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pws)) {
            showToast("验证码或手机号不能为空");
            return;
        }
        SharedPreferencesUtils.putString("phone", phone);
        SharedPreferencesUtils.putString("pws", pws);
        SharedPreferencesUtils.putBoolean("isLogin", true); //是否需要自动登录*/

        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
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
