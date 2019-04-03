package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityLoginBinding;
import com.sy.chainproject.utils.SharedPreferencesUtils;

/**
 * @ data  2019/3/21 15:15
 * @ author  zxcg
 */
public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private String name, pws;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_login, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityLoginBinding) bindings;
        setColor(getResources().getColor(R.color.white));
        setBaseVisibility(R.id.base_rl);
        if (SharedPreferencesUtils.getBoolean("isLogin")) {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            setColor(getResources().getColor(R.color.colorPrimary));
            setBaseVisibility(View.GONE);
            setBaseVisibility(R.id.base_exit);
            binding.loginButton.setOnClickListener(this);
            binding.loginForget.setOnClickListener(this);
            binding.loginRegistration.setOnClickListener(this);
        }
    }

    /**
     * 登录模块
     */
    private void login() {
        name = binding.loginName.getText().toString();
        pws = binding.loginPassword.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pws)) {
            showToast(getString(R.string.login_tips));
        } else {
            SharedPreferencesUtils.putString("phone", name);
            SharedPreferencesUtils.putString("pws", pws);
            SharedPreferencesUtils.putBoolean("isLogin", true); //是否需要自动登录
            startActivity(new Intent(this, MainActivity.class));
        }
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.login_forget:
                startActivity(new Intent(this, ForgetActivity.class));
                break;
            case R.id.login_registration:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
        finish();
    }
}
