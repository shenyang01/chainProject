package com.sy.chainproject.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import com.sy.chainproject.R;

/**
 * @ data  2019/3/29 11:21
 * @ author  zxcg
 * 相机弹出框
 */
public class CameraPopupWindow extends PopupWindow {

    public CameraPopupWindow(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.camera_popopwindow, null);
        setContentView(view);

        //this.setOutsideTouchable(true);
        this.setTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.white));
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.pop_animation);
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(parent, Gravity.BOTTOM, 0, 30);
        } else {
            this.dismiss();
        }
    }


}
