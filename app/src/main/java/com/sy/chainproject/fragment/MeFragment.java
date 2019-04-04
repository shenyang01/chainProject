package com.sy.chainproject.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.sy.chainproject.R;
import com.sy.chainproject.activity.DownActivity;
import com.sy.chainproject.activity.LoginActivity;
import com.sy.chainproject.base.BaseFragment;
import com.sy.chainproject.camera.GetPhotoFromPhotoAlbum;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.FragmentMeBinding;
import com.sy.chainproject.utils.SharedPreferencesUtils;
import com.sy.chainproject.view.CameraPopupWindow;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

/**
 * @ data  2019/3/20 9:25
 * @ author  zxcg
 */
public class MeFragment extends BaseFragment implements View.OnClickListener,EasyPermissions.PermissionCallbacks  {
    private FragmentMeBinding binding;
    private CameraPopupWindow popupWindow;
    private File cameraSavePath;
    private Uri uri;

    @Override
    public int getContent() {
        return R.layout.fragment_me;
    }
    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (FragmentMeBinding) bindings;
        binding.meGo.setOnClickListener(this);
        binding.meDown.setOnClickListener(this);
        binding.meEdition.setOnClickListener(this);
        binding.meExitLogin.setOnClickListener(this);
        binding.meHelp.setOnClickListener(this);

        try {
            if (getActivity() != null) {
                PackageInfo info = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                binding.meVersion.setText(info.versionName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_down:
                startActivity(new Intent(getActivity(), DownActivity.class));
                break;
            case R.id.me_edition:  //软件版本
                break;
            case R.id.me_exit_login:
                SharedPreferencesUtils.putBoolean("isLogin", false); //是否需要自动登录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.me_help:
                break;
            case R.id.me_go:
                //请求权限
                String[] permissions = new String[]{Manifest.permission.CAMERA};
                EasyPermissions.requestPermissions(this, getString(R.string.exception), Constants.REQUESTCODE, permissions);
                break;
            case R.id.popup_camera:
                goCamera();
                break;
            case R.id.popup_album:
                goPhotoAlbum();
                break;
        }
    }

    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    //激活相机操作
    private void goCamera() {
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(Objects.requireNonNull(getActivity()), "com.sy.chainproject.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        dismiss();
        String photoPath;
        Log.e("tag", "requestCode  " + requestCode + "  resultCode  " + resultCode);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            Log.d("拍照返回图片路径:", photoPath);
            Glide.with(this).load(photoPath).into(binding.meImage);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(getActivity(), data.getData());
            Glide.with(this).load(photoPath).into(binding.meImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showPopupWindow() {
        if (popupWindow == null) {
            popupWindow = new CameraPopupWindow(getActivity());
            popupWindow.getContentView().findViewById(R.id.popup_camera).setOnClickListener(this);
            popupWindow.getContentView().findViewById(R.id.popup_album).setOnClickListener(this);
        }
        if (!popupWindow.isShowing()) popupWindow.showPopupWindow(binding.meView);

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
        showPopupWindow();
        Log.e("tag", "权限请求成功  " + requestCode + perms.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getActivity(),"未允许权限，请前往设置打开",Toast.LENGTH_SHORT).show();
        Log.e("tag", "onPermissionsDenied  " + requestCode + perms.toString());
    }

    /**
     * 取消diaolog
     */
    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) popupWindow.dismiss();
    }
}
