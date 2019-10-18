package com.sy.chainproject.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.LoginData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityLoginBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.LoginPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;
import pub.devrel.easypermissions.EasyPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @ data  2019/3/21 15:15
 * @ author  zxcg
 * 用户登录
 */
public class LoginActivity extends BaseActivity implements BaseModelView.View<UserBean>, EasyPermissions.PermissionCallbacks {
    private ActivityLoginBinding binding;
    private String name, pws;
    private long currentTime;
    private LoginPresenter presenter;
    private String uuid;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_login, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityLoginBinding) bindings;
        setColor(getResources().getColor(R.color.white));
        setBaseVisibility(R.id.base_rl);

        setColor(getResources().getColor(R.color.white));
        setBaseVisibility(View.GONE);
        setBaseVisibility(R.id.base_exit);
        binding.loginButton.setOnClickListener(this);
        binding.loginForget.setOnClickListener(this);
        binding.loginRegistration.setOnClickListener(this);
        //请求权限
        String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE};
        EasyPermissions.requestPermissions(this, getString(R.string.exception), Constants.REQUESTCODE, permissions);
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
            LoginData logindata = new LoginData();
            logindata.setUsername(name);
            logindata.setPasswd(pws);
            logindata.setIMEI(uuid);
            HashMap<String, String> map = new HashMap<>();
            map.put(Constants.DATA, new Gson().toJson(logindata));
            presenter = new LoginPresenter(this);
            presenter.getData(RetrofitFactory.getInstance().API().login(map), 0);
        }
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
                finish();
                break;
            case R.id.login_registration:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() - currentTime < 1000) {
            finish();
        }
        currentTime = System.currentTimeMillis();
        //super.onBackPressed();
    }

    @Override
    public void updateData(UserBean data, int flags) {
        SharedPreferencesUtils.setUserdata(Constants.USERDATA, data);
        SharedPreferencesUtils.putString("phone", name);
        SharedPreferencesUtils.putString("pws", pws);
        SharedPreferencesUtils.putBoolean(Constants.ISLOGIN, true); //是否需要自动登录
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onDestroy() {
        removePresenter(presenter);
        super.onDestroy();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uuid = tm.getDeviceId();
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        SharedPreferencesUtils.putString(Constants.IMEI, uuid);
        LogUtils.e("tag", "uuid  " + uuid);
        // TODO: 后续修改
        SharedPreferencesUtils.putString(Constants.IMEI, "123321");

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        uuid = UUID.randomUUID().toString();
    }
}
