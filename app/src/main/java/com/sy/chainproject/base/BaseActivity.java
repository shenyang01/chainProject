package com.sy.chainproject.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import com.sy.chainproject.R;
import com.sy.chainproject.databinding.ActivityBaseBinding;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Toast toast = null;
    private ActivityBaseBinding binding;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // setColor(this,getResources().getColor(R.color.bg_title_bar));
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        init();

        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        LogUtils.e(metrics.density + "   " + metrics.widthPixels + "  " + metrics.heightPixels+"" +
                " densityDpi " +metrics.densityDpi+"  density "+metrics.density);*/
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.base_exit) finish();
    }

    /**
     * 初始化控件
     */
    private void init() {
        binding.baseExit.setOnClickListener(this);
        // 添加内容文件
        View view = getContent();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ViewDataBinding dataBinding = DataBindingUtil.bind(view);
        binding.baseContent.addView(view, params);
        initView(dataBinding);
    }

    public abstract View getContent();

    public abstract void initView(ViewDataBinding bindings);

    public void showToast(String string) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(string);
            toast.show();
        }
    }

    /**
     * @param view 点击空白处强制隐藏键盘
     */
    public void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
        if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN); //强制隐藏键盘
    }

    /**
     */
    public void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0及以上，不设置透明状态栏，设置会有半透明阴影
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(color);
            binding.baseRl.setBackgroundColor(color);
        }
    }

    /**
     * 控件显示及隐藏
     */
    public void setBaseVisibility(int id) {
        switch (id) {
            case R.id.base_exit:
                binding.baseExit.setVisibility(View.GONE);
                break;
            case R.id.base_name:
                binding.baseName.setVisibility(View.GONE);
                break;
            case R.id.base_rl:
                binding.baseRl.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 设置tile
     */
    public void setBaseTitle(String title) {
        binding.baseExit.setText(title);
    }

    /**
     * 倒计时
     */
    public void countDown(int count) {
        timer = new CountDownTimer(count * 1000, 1000) {
            @Override
            public void onTick(long l) {
                binding.baseName.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) timer.cancel();
    }
}