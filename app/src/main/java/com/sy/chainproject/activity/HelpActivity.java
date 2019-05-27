package com.sy.chainproject.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.PicturesAdapter;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityHelpBinding;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ data  2019/4/29 15:15
 * @ author  zxcg
 * 帮助与反馈
 */

public class HelpActivity extends BaseActivity implements PicturesAdapter.ViewOnclick {
    private ActivityHelpBinding binding;
    private PicturesAdapter adapter;
    private static final int REQUEST_CODE_CHOOSE = 200;
    private List<Uri> mSelected;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_help, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.help_feedback));
        binding = (ActivityHelpBinding) bindings;
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        binding.helpRv.setLayoutManager(manager);

        binding.helpRv.setAdapter(new PicturesAdapter(this, mSelected, this));
    }

    private void selectPictures() {
        Set<MimeType> set = new HashSet<>();
        set.add(MimeType.JPEG);
        set.add(MimeType.PNG);
        Matisse.from(HelpActivity.this).choose(set) // 选择 mime 的类型
                .countable(true).maxSelectable(9) // 图片选择的最多数量
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_100)).restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED).thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            binding.helpRv.setAdapter(new PicturesAdapter(this, mSelected, this));
        }
    }

    @Override
    public void clickView(View v, int position) {
        showToast(position + "");
        if (mSelected == null || position == mSelected.size()) {
            selectPictures();
        }
    }
}
