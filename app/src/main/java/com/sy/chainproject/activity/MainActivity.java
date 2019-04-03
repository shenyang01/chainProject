package com.sy.chainproject.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import androidx.annotation.RequiresApi;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.R;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityMainBinding;
import com.sy.chainproject.download.DownLoadServer;
import com.sy.chainproject.fragment.CoordinateFragment;
import com.sy.chainproject.fragment.HomeFragment;
import com.sy.chainproject.fragment.MeFragment;
import com.sy.chainproject.fragment.ProductFragment;
import pub.devrel.easypermissions.EasyPermissions;


import java.util.List;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, EasyPermissions.PermissionCallbacks {
    private ActivityMainBinding binding;
    private int mindex;
    private FragmentTransaction transaction;
    private Fragment fragment[];
    private String tag[];
    private MeFragment meFragment;
    private long currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.mainRG.setOnCheckedChangeListener(this);
        setBaseVisibility(R.id.base_exit);
        //不为null，说明是死而复活，移除已经存在的fragment
        if (savedInstanceState != null) {
            FragmentManager mManager = getSupportFragmentManager();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(mManager.findFragmentByTag(Constants.HOMEFRAGMENT));
            transaction.remove(mManager.findFragmentByTag(Constants.PRODUCTFRAGMENT));
            transaction.remove(mManager.findFragmentByTag(Constants.COORDINATEFRAGMENT));
            transaction.remove(mManager.findFragmentByTag(Constants.MEFRAGMENT));
            transaction.commit();
        }
    }

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_main, null);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityMainBinding) bindings;
        setColor(getResources().getColor(R.color.bg_title_bar));
        HomeFragment homeFragment = new HomeFragment();
        ProductFragment productFragment = new ProductFragment();
        CoordinateFragment coordinateFragment = new CoordinateFragment();
        meFragment = new MeFragment();
        tag = new String[]{Constants.HOMEFRAGMENT, Constants.PRODUCTFRAGMENT, Constants.COORDINATEFRAGMENT, Constants.MEFRAGMENT};
        fragment = new Fragment[]{homeFragment, productFragment, coordinateFragment, meFragment};
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragment, fragment[0], tag[0]).commit();

        //请求权限
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        EasyPermissions.requestPermissions(this, getString(R.string.exception), Constants.REQUESTCODE, permissions);
        //startInstallPermissionSettingActivity();
}

    private void showFragment(int index) {
        transaction = getSupportFragmentManager().beginTransaction();
        if (this.mindex == index) return;
        transaction.hide(fragment[mindex]);
        if (!fragment[index].isAdded()) {
            transaction.add(R.id.main_fragment, fragment[index], tag[index]).show(fragment[index]);
        } else {
            transaction.show(fragment[index]);
        }
        transaction.commit();
        mindex = index;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_home:
                showFragment(0);
                setBaseTitle(getResources().getString(R.string.home));
                break;
            case R.id.main_product:
                showFragment(1);
                setBaseTitle(getResources().getString(R.string.product));
                break;
            case R.id.main_coordinate:
                showFragment(2);
                setBaseTitle(getResources().getString(R.string.coordinate));
                updateAPK();
                break;
            case R.id.main_me:
                showFragment(3);
                setBaseVisibility(R.id.base_rl);
                break;
        }
    }

    /**
     * 下载apk
     */
    private void updateAPK() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.update));
        builder.setMessage("gagaggaga\nfgdgsgsg\nfdsga");
        builder.setPositiveButton(getString(R.string.ok), (dialog, which) -> startService(new Intent(MainActivity.this, DownLoadServer.class))).setNegativeButton(getString(R.string.canle), (dialog, which) -> {

        }).show();
    }

    @Override
    public void onClick(View v) {
        // super.onClick(v);
    }

    @Override
    public void onBackPressed() {
        meFragment. dismiss();
        if(System.currentTimeMillis()-currentTime<1000){
            finish();
        }
        currentTime=System.currentTimeMillis();
        //super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.e("tag", "onPermissionsGranted  " + requestCode + perms.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //showToast("未允许权限，请前往设置打开");
        Log.e("tag", "onPermissionsDenied  " + requestCode + perms.toString());
    }

    /**
     * 开启设置安装未知来源应用权限界面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //跳转到允许安装未知应用
            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (!hasInstallPermission) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                startActivityForResult(intent, 0);
            }
        }
    }
}
