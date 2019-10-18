package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.RetailBean;
import com.sy.chainproject.bean.RetailData;
import com.sy.chainproject.bean.RetailPOSData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityRetailBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.RetailPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/6/3 15:02
 * @ author  zxcg
 * 零售页面
 */
public class RetailActivity extends BaseActivity implements BaseModelView.View<RetailBean> {
    private ActivityRetailBinding binding;
    private static final int requestCode = 0x11;
    private BaseAdapter adapter;
    private List<RetailBean> list; //数据源
    private BasePresenter<RetailBean, RetailBean> presenter;
    private UserBean userBean;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_retail, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.retail));
        binding = (ActivityRetailBinding) bindings;
        binding.retailBt.setOnClickListener(this);
        binding.retailScanCode.setOnClickListener(this);
        binding.retailSearch.setOnClickListener(this);
        binding.retailHistory.setOnClickListener(this);
        list = new ArrayList<>();
        presenter = new RetailPresenter(this);
        userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        setAdapter();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.retail_scan_code:
                startActivityForResult(new Intent(RetailActivity.this, CaptureActivity.class), requestCode);
                break;
            case R.id.retail_search:
                startActivityForResult(new Intent(RetailActivity.this, QueryActivity.class), requestCode);
                break;
            case R.id.retail_history:
                startActivity(new Intent(RetailActivity.this, RetailHistoryActivity.class));
                break;
            case R.id.retail_bt:
                getPosData();
                break;
        }
    }

    private void setAdapter() {
        binding.retailRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseAdapter<RetailBean>(this, R.layout.rv_item_retail, list) {
            @Override
            public void convert(BaseViewHolder holder, RetailBean data, int position) {
                holder.setText(R.id.item_retail_name, data.getStyleName());
                holder.setText(R.id.item_retail_color, data.getColorName());
                holder.setText(R.id.item_retail_size, data.getSizeName());
                holder.setText(R.id.item_retail_num, data.getQty() + "");
                holder.setText(R.id.item_retail_price, data.getSellPrice());
            }
        };
        binding.retailRv.setAdapter(adapter);
    }

    /**
     * 扫描条码提交
     */
    private void getRetailData(String string) {
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

    /**
     * POS 零售提交
     */
    private void getPosData() {
        if (list == null || list.size() == 0) {
            showToast(getString(R.string.POS_tips));
            return;
        }
        UserBean userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        HashMap<String, String> map = new HashMap<>();
        RetailPOSData data = new RetailPOSData();
        RetailPOSData.DataBean bean;
        data.setUserid(userBean.getUserid() + "");
        String posid = System.currentTimeMillis() + (long) userBean.getUserid() + "";
        data.setPosid(posid);
        data.setCurrency("RMB");
        data.setFlag(1);

        List<RetailPOSData.DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            bean = new RetailPOSData.DataBean();
            data.setPosid(posid);
            bean.setStyleid(list.get(i).getStyleid());
            bean.setQty(list.get(i).getQty());
            bean.setSellPrice(list.get(i).getSellPrice());
            bean.setDiscount(list.get(i).getDiscount());
            bean.setSubAmount(list.get(i).getSubAmount());
            bean.setPosAmount(list.get(i).getPosAmount());
            bean.setShopid(list.get(i).getShopid() + "");
            bean.setColorid(list.get(i).getColorid());
            bean.setSizeid(list.get(i).getSizeid() + "");
            bean.setPlotid(list.get(i).getPlotid() + "");
            bean.setBatch(list.get(i).getBatch());
            dataBeans.add(bean);
        }
        data.setData(dataBeans);
        map.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getPosData(map), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.SETRESULT) {
            String str = data.getStringExtra("data");
            getRetailData(str);
        } else if (resultCode == Constants.SETRESULT_Q) { //查询返回
            list.add((RetailBean) data.getSerializableExtra("bean"));
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 计算应收总金额
     */
    private void addMoney() {
        int money=0;
        for (RetailBean m : list) {
            if (TextUtils.isEmpty(m.getSellPrice())) continue;
            money =money +Integer.valueOf(m.getSellPrice());
            LogUtils.e(money+"   "+m.getSellPrice());
        }
        binding.retailReceivable.setText(String.valueOf(money));
    }

    @Override
    public void updateData(RetailBean data, int flags) {
        if (flags == 0) {
            if (data.getQty() == 0) {
                showToast(getString(R.string.no_style));
            } else if (data.getQty() == -1) {
                showToast(getString(R.string.no_style2));
            } else {
                list.add(data);
                adapter.notifyDataSetChanged();
                addMoney();
            }
        } else if (flags == 1) {
            showToast("提交成功");
        }
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }
}
