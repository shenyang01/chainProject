package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.RetailBean;
import com.sy.chainproject.bean.RetailData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityCodeResultsBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.RetailPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.util.HashMap;

/**
 * @ data  2019/4/8 9:33
 * @ author  zxcg
 * 扫码结果
 * TODO:  条码扫码结果 后续修改
 */

public class CodeResultsActivity extends BaseActivity implements BaseModelView.View<RetailBean>{
    private ActivityCodeResultsBinding binding;
    private BasePresenter<RetailBean, RetailBean> presenter;
    private String data;
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_code_results,null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.code_info));
        binding= (ActivityCodeResultsBinding) bindings;
        data = getIntent().getStringExtra("data");
        presenter= new RetailPresenter(this);
        getRetailData(data);
    }
    /**
     * 扫描条码提交
     */
    private void getRetailData(String string) {
        UserBean userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        if (string == null) return;
        HashMap<String, String> map = new HashMap<>();
        RetailData data = new RetailData();
        data.setBarCode(string);
        data.setUserid(userBean.getUserid());
        data.setQty(1);
        map.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getRetailData(map), 0);
    }
    @Override
    public void updateData(RetailBean data, int flags) {
        binding.codeNumber.setText(data.getBarCode());
        binding.codeTime.setText(data.getDtime());
        binding.codeBrand.setText(String.valueOf(data.getBatch()));
        binding.codeStyleNo.setText(data.getStyleNo());
        binding.codeStyleName.setText(data.getStyleName());
        binding.codeColor.setText(data.getColorName());
        binding.codeSize.setText(data.getSizeName());
        binding.codePrice.setText(data.getSellPrice());
    }

    @Override
    public void onFailure(String e, int flags) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }
}
