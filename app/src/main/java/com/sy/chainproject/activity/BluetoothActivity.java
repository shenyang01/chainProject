package com.sy.chainproject.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.core.bluetooth.BluetoothUtils;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityBluetoothBinding;
import com.sy.chainproject.interfance.BluetoothResult;
import pub.devrel.easypermissions.EasyPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * 蓝牙页面
 */
public class BluetoothActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, BluetoothResult<BluetoothDevice>, BaseViewHolder.ViewOnclick, EasyPermissions.PermissionCallbacks {
    private ActivityBluetoothBinding binding;
    private BluetoothUtils utils;
    private List<BluetoothDevice> devices;
    private BaseAdapter<BluetoothDevice> adapter = null;

    //progressDialog = ProgressDialog.show(context, "请稍等...", "搜索蓝牙设备中...", true);
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_bluetooth, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityBluetoothBinding) bindings;
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devices = new ArrayList<>();
        utils = new BluetoothUtils(this, this);
        if (bluetoothAdapter == null) {
            showToast(getString(R.string.nonsupport_bluetooth));
            return;
        }
        binding.bluePaired.setLayoutManager(new LinearLayoutManager(this));
        String name = bluetoothAdapter.getName();
        binding.blueName.setText(name);
        binding.blueToggle.setOnCheckedChangeListener(this);
        if(utils.bState()){
            binding.blueToggle.setChecked(true);
            utils.searchDevices();
        }
        //请求权限
        String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        EasyPermissions.requestPermissions(this, getString(R.string.exception), Constants.REQUESTCODE, permissions);

    }


    private void setAdapter(List<BluetoothDevice> devices) {
        if (adapter == null) {
            adapter = new BaseAdapter<BluetoothDevice>(this, R.layout.rv_item_bluetooth, devices, this) {
                @Override
                public void convert(BaseViewHolder holder, BluetoothDevice data, int position) {
                    holder.setText(R.id.blue_item, data.getName() + "\t" + data.getAddress());
                    holder.setOnclick(R.id.blue_item, position);
                }
            };
            binding.bluePaired.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) utils.openBluetooth();
        else utils.closeBluetooth();
    }

    @Override
    public void success(List<BluetoothDevice> devices) {
        this.devices = devices;
        setAdapter(devices);
    }


    @Override
    public void fail(List<BluetoothDevice> devices) {
        setAdapter(devices);
    }

    @Override
    public void clickView(View v, int position) {
        if (devices.size() == 0) return;
        utils.connect(devices.get(position));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 权限请求成功
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //showToast("未允许权限，请前往设置打开");
        Log.e("tag", "onPermissionsDenied  " + requestCode + perms.toString());
    }

    @Override
    protected void onDestroy() {
        utils.unregisterReceiver();
        super.onDestroy();
    }
}
