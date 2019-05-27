package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.google.zxing.BarcodeFormat;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.core.bluetooth.BluetoothUtils;
import com.sy.chainproject.databinding.ActivityGenerateCodeBinding;
import com.sy.chainproject.utils.TypedValueUtils;
import com.sy.chainproject.zxing.utils.ZxingUtils;

/**
 * @ data  2019/4/8 10:44
 * @ author  zxcg
 * 生成条码 code  0  一维码  1  二维码
 */

public class GenerateCodeActivity extends BaseActivity {
    private ActivityGenerateCodeBinding binding;
    private ZxingUtils utils;
    private int code;
    private Bitmap bitmap;
    private int index;  //是否可编辑

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_generate_code, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityGenerateCodeBinding) bindings;
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.generate));
        Intent intent = getIntent();
        if (intent == null) return;
        code = intent.getIntExtra("code", 0);
        utils = new ZxingUtils();
        binding.generate.setOnClickListener(this);
        binding.generatePrint.setOnClickListener(this);
        if (code == 0) {
            binding.generateInfo.setText(getString(R.string.code_info).concat(getString(R.string.text_value)));
            bitmap = utils.createBitmap("11111", BarcodeFormat.CODE_128, TypedValueUtils.dpTopx(this, 300), TypedValueUtils.dpTopx(this, 200));
            binding.generateImage.setImageBitmap(bitmap);
        } else {
            binding.generateInfo.setText(getString(R.string.code_info).concat(getString(R.string.text_value)));
            bitmap = utils.createBitmap("11111", BarcodeFormat.QR_CODE, TypedValueUtils.dpTopx(this, 300), TypedValueUtils.dpTopx(this, 200));
            binding.generateImage.setImageBitmap(bitmap);
        }
        binding.settingNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.generate:
                createBitmap();
                break;
            case R.id.generate_print:
                int num;
                String codeNumber = binding.codeNumber.getText().toString().trim();
                if (TextUtils.isEmpty(codeNumber)) num = 1;
                else num = Integer.parseInt(codeNumber);
                BluetoothUtils utils = new BluetoothUtils(this);
                utils.send(utils.compressPic(bitmap),num);
                break;
            case R.id.setting_number:
                index++;
                if (index % 2 == 0) {
                    binding.codeNumber.setEnabled(false);
                    binding.settingNumber.setText(getString(R.string.print_number));
                    binding.codeNumber.setBackgroundResource(R.drawable.background_box);
                } else {
                    binding.codeNumber.setEnabled(true);
                    binding.codeNumber.setBackgroundResource(R.drawable.background_box_blue);
                    binding.settingNumber.setText(getString(R.string.ok));
                }
                break;
        }
    }

    /**
     * 生成条码
     **/
    private void createBitmap() {
        String info = binding.generateCode.getText().toString();

        if (TextUtils.isEmpty(info)) {
            showToast(getString(R.string.generate_err));
            return;
        }
        binding.generateInfo.setText(getString(R.string.code_info).concat(info));
        if (code == 0) {
            bitmap = utils.createBitmap(info, BarcodeFormat.CODE_128, TypedValueUtils.dpTopx(this, 300), TypedValueUtils.dpTopx(this, 200));
            binding.generateImage.setImageBitmap(bitmap);
        } else {
            bitmap = utils.createBitmap(info, BarcodeFormat.QR_CODE, TypedValueUtils.dpTopx(this, 300), TypedValueUtils.dpTopx(this, 200));
            binding.generateImage.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        bitmap.recycle();
        bitmap = null;
        super.onDestroy();
    }
}
