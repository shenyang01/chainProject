package com.sy.chainproject.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.zxing.Result;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityCaptureBinding;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.zxing.camera.CameraManager;
import com.sy.chainproject.zxing.decode.DecodeThread;
import com.sy.chainproject.zxing.utils.CaptureActivityHandler;
import com.sy.chainproject.zxing.utils.InactivityTimer;

import java.lang.reflect.Field;

/**
 * 二维码扫描界面
 *
 * @author zxcn
 */
public final class CaptureActivity extends BaseActivity implements SurfaceHolder.Callback {

    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private InactivityTimer inactivityTimer;
    private SurfaceView scanPreview = null;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private Rect mCropRect = null;
    private boolean isHasSurface = false;
    private boolean first;
    private static final String TAG = "CaptureActivity";

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_capture, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        ActivityCaptureBinding binding = (ActivityCaptureBinding) bindings;
        setColor(getResources().getColor(R.color.cap_bg));
        setBaseBask(getResources().getString(R.string.black));
        scanPreview = findViewById(R.id.capture_preview);
        scanContainer = findViewById(R.id.capture_container);
        scanCropView = findViewById(R.id.capture_crop_view);
        ImageView scanLine = findViewById(R.id.capture_scan_line);
        inactivityTimer = new InactivityTimer(this);
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.9f);
        animation.setDuration(4500);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        scanLine.startAnimation(animation);
        checkBluetoothPermission();
        timer.start();
    }


    public Handler getHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }


    @Override
    protected void onResume() {
        super.onResume();
        inactivityTimer.onResume();
        if (first && cameraManager == null) {
            cameraManager = new CameraManager(getApplication());
            initCamera(scanPreview.getHolder());
        }
        first = true;
    }

    private void checkBluetoothPermission() {
        //Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(CaptureActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                LogUtils.e(TAG, "CAMERA  " + " 权限请求中  " + Build.VERSION.SDK_INT);
                ActivityCompat.requestPermissions(CaptureActivity.this, new String[]{Manifest.permission.CAMERA}, 123);
            } else {
                cameraManager = new CameraManager(getApplication());
                LogUtils.e(TAG, " 有权限  ");
                handler = null;
                if (isHasSurface) {
                    initCamera(scanPreview.getHolder());
                } else {
                    scanPreview.getHolder().addCallback(this);
                }
            }
        }
    }

    /**
     * 权限请求完成
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.e(TAG, " 权限请求完成  ");
        cameraManager = new CameraManager(getApplication());
        handler = null;
        if (!isHasSurface) {
            initCamera(scanPreview.getHolder());
        } else {
            scanPreview.getHolder().addCallback(this);
        }
    }


    @Override
    protected void onPause() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        if (cameraManager != null) {
            cameraManager.closeDriver();
            cameraManager = null;
        }
        if (!isHasSurface) {
            scanPreview.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    /**
     * 30 超时返回
     */
    private CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        first = false;
        scanPreview.getHolder().removeCallback(this);
        scanPreview = null;
        inactivityTimer.shutdown();
        timer.cancel();
        timer = null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!isHasSurface) {
            isHasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isHasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 解析获取扫码后的数据
     */
    public void handleDecode(Result rawResult, Bundle bundle) {
        inactivityTimer.onActivity();
        String info = rawResult.getText();
        LogUtils.e(TAG, info);
        Intent intent = new Intent(this, CodeResultsActivity.class);
        intent.putExtra("result_code", "");
        startActivity(intent);
        finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            if (handler == null) {
                handler = new CaptureActivityHandler(CaptureActivity.this, cameraManager, DecodeThread.ALL_MODE); //QR码模式
            }
            initCrop();
        } catch (Exception e) {
            e.printStackTrace();
            displayFrameworkBugMessageAndExit();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void displayFrameworkBugMessageAndExit() {
        cameraManager.closeDriver();
        finish();

    }


    public Rect getCropRect() {
        return mCropRect;
    }

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = cameraManager.getCameraResolution().y;
        int cameraHeight = cameraManager.getCameraResolution().x;
        LogUtils.e(TAG, "initCrop()  " + cameraWidth + "   " + cameraHeight);

        /* 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /* 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /* 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /* 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /* 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /* 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /* 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
        LogUtils.e(TAG, " mCropRect  " + mCropRect.toString());
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}