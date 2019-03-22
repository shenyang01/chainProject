package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityLoginBinding;

/**
 * @ data  2019/3/21 15:15
 * @ author  zxcg
 */
public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private String name,pws;
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_login, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityLoginBinding) bindings;
        setColor(this, getResources().getColor(R.color.colorPrimary));
        setBaseVisibility(View.GONE);
        setBaseVisibility(R.id.base_exit);
        binding.loginButton.setOnClickListener(this);
        binding.loginForget.setOnClickListener(this);
        binding.loginRegistration.setOnClickListener(this);
    }

    /**
     * 登录模块
     */
    private void login(){
        name = binding.loginName.getText().toString();
        pws = binding.loginPassword.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pws))
        {
            showToast(getString(R.string.login_tips));
        }else{
            startActivity(new Intent(this,MainActivity.class));
        }
        startActivity(new Intent(this,MainActivity.class));
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.login_forget:
                showToast("忘记密码");
                break;
            case R.id.login_registration:
                showToast("注册");
                break;
        }
    }
}
