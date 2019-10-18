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
import com.sy.chainproject.bean.TransshipmentBean;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.bean.VIPData;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityTransshipmentBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.TransshipmentPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/7/29 14:25
 * @ author  zxcg
 * 调货单
 */
public class TransshipmentActivity extends BaseActivity implements BaseModelView.View<List<TransshipmentBean>> {
    private ActivityTransshipmentBinding binding;
    private BasePresenter<TransshipmentBean, List<TransshipmentBean>> presenter;
    private TimePickerView pvTime;
    private UserBean userBean;
    private BaseAdapter<TransshipmentBean> adapter;
    private List<TransshipmentBean> list;


    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_transshipment, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.transshipment));
        binding = (ActivityTransshipmentBinding) bindings;
        binding.transitionDate.setText(getTime(new Date()));
        binding.transitionDate.setOnClickListener(this);
        binding.transitionAdd.setOnClickListener(this);
        userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        presenter = new TransshipmentPresenter(this);
        list = new ArrayList<>();
        getTransferStoreInfo(0);
    }

    /**
     * 设置适配器
     */
    private void setAdapter(List<TransshipmentBean> data) {
        binding.transitionRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseAdapter<TransshipmentBean>(this, R.layout.rv_item_transshipment, data) {
            @Override
            public void convert(BaseViewHolder holder, TransshipmentBean data, int position) {
                holder.setText(R.id.item_transition_number, data.getStoreNum());
                holder.setText(R.id.item_transition_time, data.getDtime().substring(0,10));
                holder.setText(R.id.item_transition_style, getString(R.string.styleNo).concat(data.getStyleNo()));
                holder.setText(R.id.item_transition_name,  getString(R.string.styleName).concat(data.getStyleName()));
                holder.setText(R.id.item_transition_date, getString(R.string.transshipment_deliver).concat(data.getDyDate().substring(0,10)));
                holder.setText(R.id.item_transition_type, getString(R.string.transshipment_type).concat(data.getTypeOut()));
                holder.setText(R.id.item_transition_num, getString(R.string.ct_num).concat(data.getTotalQty()+""));
                holder.setText(R.id.item_transition_advance, getString(R.string.pAmount).concat(data.getPAmount() + ""));
                holder.setText(R.id.item_transition_remark, getString(R.string.transshipment_remark).concat(data.getRemark()));
            }
        };
        binding.transitionRv.setAdapter(adapter);
    }

    /**
     * 获取调拨订单
     * flags 区分刷新
     */
    private void getTransferStoreInfo(int flags) {
        HashMap<String, String> map = new HashMap<>();
        VIPData data = new VIPData();
        String date = binding.transitionDate.getText().toString();
        data.setUserid(userBean.getUserid());
        data.setD1(date.substring(0, 8) + "01");
        data.setD2(binding.transitionDate.getText().toString());
        map.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getTransferStoreInfo(map), flags);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.transition_add:
                startActivity(new Intent(TransshipmentActivity.this,TransshipmentAddActivity.class));
                break;
            case R.id.transition_date:
                getDate();
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
            pvTime = new TimePickerBuilder(TransshipmentActivity.this, (date, v) -> {
                binding.transitionDate.setText(getTime(date));
                getTransferStoreInfo(1);
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
    public void updateData(List<TransshipmentBean> data, int flags) {
        list.clear();
        list.addAll(data);
        switch (flags) {
            case 0:
                setAdapter(data);
                break;
            case 1:
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onDestroy() {
        removePresenter(presenter);
        pvTime = null;
        super.onDestroy();
    }

}
