package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.InventoryBean;
import com.sy.chainproject.bean.InventoryData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityInventoryBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.InventoryPresenter;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/7/10 10:21
 * @ author  zxcg
 * 盘点
 */
public class InventoryActivity extends BaseActivity implements BaseModelView.View<List<InventoryBean>>, BaseViewHolder.ViewOnclick {
    private ActivityInventoryBinding binding;
    private TimePickerView pvTime;
    private BasePresenter<InventoryBean, List<InventoryBean>> presenter;
    private UserBean bean;
    private String mkid;
    private BaseViewHolder holder;
    private List<InventoryBean> list;//  明细数据源
    private BaseAdapter<InventoryBean> adapter;
    private int position;


    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_inventory, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.report_inventory));
        binding = (ActivityInventoryBinding) bindings;
        binding.inventoryDate.setOnClickListener(this);
        binding.inventoryStatistics.setOnClickListener(this);
        binding.inventoryHistorical.setOnClickListener(this);
        binding.inventoryExport.setOnClickListener(this);
        list = new ArrayList<>();
        presenter = new InventoryPresenter(this);
        bean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
    }

    private void setAdapter(List<InventoryBean> list) {
        binding.inventoryRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseAdapter<InventoryBean>(this, R.layout.rv_item_inventory, list, this) {
            @Override
            public void convert(BaseViewHolder holder, InventoryBean data, int position) {
                holder.setText(R.id.inventory_name, data.getStyleName());
                holder.setText(R.id.inventory_color, data.getColorName());
                holder.setText(R.id.inventory_size, data.getSizeName());
                holder.setText(R.id.inventory_price, data.getAmount() + "");
                holder.setText(R.id.inventory_profit, data.getDeficitQty() + "");
                holder.setText(R.id.inventory_account, data.getBookQty() + "");
                holder.setEdText(R.id.inventory_firm_offer, data.getQty() + "");
                if (data.getState().equals(getString(R.string.ct_update))) holder.setEdText(R.id.inventory_firm_offer, false);
                else holder.setEdText(R.id.inventory_firm_offer, true);
                holder.setText(R.id.inventory_ok, data.getState());
                holder.setOnclick(R.id.inventory_ok, position);
            }
        };
        binding.inventoryRv.setAdapter(adapter);
    }

    /**
     * 创建订单
     */
    private void getInventory() {
        mkid = System.currentTimeMillis() + "" + (long) bean.getUserid();
        HashMap<String, String> map = new HashMap<>();
        InventoryData data = new InventoryData();
        String date = binding.inventoryDate.getText().toString();
        data.setUserid(bean.getUserid());
        data.setDyear(date.split("-")[0]);
        data.setDmonth(date.split("-")[1]);
        data.setMkid(mkid);
        map.put(Constants.DATA, new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getInventory(map), 0);
    }

    /**
     * 获取盘点单明细
     */
    private void getInventoryDetail(String mkid) {
        HashMap<String, String> map = new HashMap<>();
        InventoryData data = new InventoryData();
        data.setMkid(mkid);
        data.setUserid(bean.getUserid());
        map.put(Constants.DATA, new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getInventoryDetail(map), 1);
    }

    /**
     * 修改实盘数
     */
    @Override
    public void clickView(View v, int position) {
        this.position = position;
            holder = (BaseViewHolder) binding.inventoryRv.findViewHolderForAdapterPosition(position);
        TextView view = holder.getView(R.id.inventory_ok);
        if (list.get(position).getState().equals(getString(R.string.ct_update))) {
            view.setText(getString(R.string.ok));
            list.get(position).setState(getString(R.string.ok));
            holder.getView(R.id.inventory_firm_offer).setEnabled(true);
        } else {
            TextView textView = holder.getView(R.id.inventory_firm_offer);
            HashMap<String, String> map = new HashMap<>();
            InventoryData data = new InventoryData();
            data.setUserid(bean.getUserid());
            data.setMkey(list.get(position).getMkey());
            data.setQty(textView.getText().toString());
            map.put(Constants.DATA, new Gson().toJson(data));
            presenter.getData(RetrofitFactory.getInstance().API().setInventoryDetail(map), 2);
        }
    }

    boolean is = false;

    @Override
    public void updateData(List<InventoryBean> data, int flags) {
        switch (flags) {
            case 0:
                getInventoryDetail(mkid);
                break;
            case 1:
                if (!is) {
                    list.clear();
                    list.addAll(data);
                    setAdapter(list);
                    is = !is;
                } else {
                    list.get(position).setBookQty(data.get(position).getBookQty());
                    list.get(position).setQty(data.get(position).getQty());
                    list.get(position).setAmount(data.get(position).getAmount());
                    list.get(position).setDeficitQty(data.get(position).getDeficitQty());
                    list.get(position).setState(getString(R.string.ct_update));
                    holder.getView(R.id.inventory_firm_offer).setEnabled(false);
                    adapter.notifyItemChanged(position);
                }
                break;
            case 2:
                showToast(getString(R.string.successful_submission));
                getInventoryDetail(mkid);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.inventory_date:
                getDate();
                break;
            case R.id.inventory_statistics:
                if (TextUtils.isEmpty(binding.inventoryDate.getText())) {
                    showToast("请选择时间");
                    return;
                }
                is = false;
                getInventory();
                break;
            case R.id.inventory_historical:
                Intent intent = new Intent(InventoryActivity.this, HistoryStatementActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, Constants.SETRESULT);
                break;
            case R.id.inventory_export:

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
            Calendar startDate = Calendar.getInstance();
            startDate.set(2019, 0, 1);
            Calendar endDate = Calendar.getInstance();
            endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), 1);
            pvTime = new TimePickerBuilder(InventoryActivity.this, (date, v) -> {
                binding.inventoryDate.setText(getTime(date));
            }).setType(new boolean[]{true, true, false, false, false, false}).setRangDate(startDate, endDate).setDate(endDate).setSubmitColor(getResources().getColor(R.color.colorAccent)).build();
            pvTime.show();
        } else pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        return format.format(date);
    }


    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.SETRESULT_Q) {
            mkid = data.getStringExtra("mkid");
            binding.inventoryDate.setText(data.getStringExtra("date"));
            getInventoryDetail(mkid);
        }
    }

    @Override
    protected void onDestroy() {
        removePresenter(presenter);
        super.onDestroy();
    }

}
