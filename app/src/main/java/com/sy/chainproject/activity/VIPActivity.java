package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.bean.VIPBean;
import com.sy.chainproject.bean.VIPData;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityVipBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.VIPPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;
import com.sy.chainproject.view.DividerDecoration;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/7/23 14:43
 * @ author  zxcg
 * VIP 页面
 */
public class VIPActivity extends BaseActivity implements BaseModelView.View<List<VIPBean>> {
    private ActivityVipBinding binding;
    private BasePresenter<VIPBean, List<VIPBean>> presenter;
    private TimePickerView pvTime;
    private UserBean userBean;
    private BaseAdapter<VIPBean> adapter;
    private List<VIPBean> list;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_vip, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.vip2));
        setBaseRight(getString(R.string.vip_register));
        binding = (ActivityVipBinding) bindings;
        binding.vipDate.setText(getTime(new Date()));
        binding.vipDate.setOnClickListener(this);
        binding.vipQuery.setOnClickListener(this);
        presenter = new VIPPresenter(this);
        userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        list = new ArrayList<>();
        getVipRegInfo();
    }

    private void setAdapter(List<VIPBean> data) {
        binding.vipRv.setLayoutManager(new LinearLayoutManager(this));
        binding.vipRv.addItemDecoration(new DividerDecoration(this));
        adapter = new BaseAdapter<VIPBean>(this, R.layout.rv_item_vip, data) {
            @Override
            public void convert(BaseViewHolder holder, VIPBean data, int position) {
                holder.setText(R.id.item_vip_phone, data.getPhone());
                holder.setText(R.id.item_vip_name, data.getCustomerName());
                holder.setText(R.id.item_vip_card, data.getLevelName());
                holder.setText(R.id.item_vip_integral, data.getTotalIntegral() + "");
                holder.setText(R.id.item_vip_balance, data.getAmount() + "");
                holder.setText(R.id.item_vip_store, data.getShopName());
                holder.setText(R.id.item_vip_date, data.getDtime().substring(0, 10));
            }
        };
        binding.vipRv.setAdapter(adapter);
    }

    /**
     * 获取用户数据
     */
    private void getVipRegInfo() {
        HashMap<String, String> map = new HashMap<>();
        VIPData data = new VIPData();
        String date = binding.vipDate.getText().toString();
        data.setUserid(userBean.getUserid());
        data.setD1(date.substring(0, 8) + "01");
        data.setD2(date);
        data.setPhone(binding.vipPhone.getText().toString());
        map.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getVipRegInfo(map), 0);
    }

    private boolean is;

    @Override
    public void updateData(List<VIPBean> data, int flags) {
        list.clear();
        list.addAll(data);
        if (!is) {
            setAdapter(list);
            is = !is;
        } else adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String e, int flags) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.vip_date:
                getDate();
                break;
            case R.id.vip_query:
                getVipRegInfo();
                break;
            case R.id.base_right:
                startActivityForResult(new Intent(VIPActivity.this, VIPRegisterActivity.class), Constants.REQUESTCODE);
                break;
        }
    }

    /**
     * 时间选择器
     */
    private void getDate() {
        //时间选择器
        if (pvTime == null) {
            Calendar selectedDate = Calendar.getInstance();//系统当前时间
            Calendar startDate = Calendar.getInstance();  //日期选择器起始日期
            startDate.set(2019, 0, 1);
            Calendar endDate = Calendar.getInstance();// //日期选择器结束日期
            endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
            pvTime = new TimePickerBuilder(VIPActivity.this, (date, v) -> {
                binding.vipDate.setText(getTime(date));
            }).setType(new boolean[]{true, true, true, false, false, false}).setDate(endDate).setRangDate(startDate, endDate).
                    setSubmitColor(getResources().getColor(R.color.colorAccent)).build();
            pvTime.show();
        } else pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.SETRESULT) getVipRegInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }
}
