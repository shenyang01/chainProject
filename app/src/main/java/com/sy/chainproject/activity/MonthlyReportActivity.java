package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.InventoryData;
import com.sy.chainproject.bean.MonthlyBean;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityMonthlyReportBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.MonthlyPresenter;
import com.sy.chainproject.utils.ExcelUtils;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/7/10 10:21
 * @ author  zxcg
 * 月报
 */
public class MonthlyReportActivity extends BaseActivity implements BaseModelView.View<List<MonthlyBean>> {
    private ActivityMonthlyReportBinding binding;
    private TimePickerView pvTime;
    private BasePresenter<MonthlyBean, List<MonthlyBean>> presenter;
    private UserBean bean;
    private String mkid;
    private String path;
    private List<MonthlyBean> list;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_monthly_report, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        path = getFilesDir() + "/haha.xls";
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.report_monthly));
        bean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        binding = (ActivityMonthlyReportBinding) bindings;
        binding.monthlyDate.setOnClickListener(this);
        binding.monthlyStatistics.setOnClickListener(this);
        binding.monthlyHistorical.setOnClickListener(this);
        binding.monthlyExport.setOnClickListener(this);
        binding.monthlyDate.setText(getTime(new Date()));
        presenter = new MonthlyPresenter(this);
        getMonthly(); // 默认显示当前月报
    }

    private void setAdapter(List<MonthlyBean> list) {
        binding.monthlyRv.setLayoutManager(new LinearLayoutManager(this));
        BaseAdapter<MonthlyBean> adapter = new BaseAdapter<MonthlyBean>(this, R.layout.rv_item_monthly, list) {
            @Override
            public void convert(BaseViewHolder holder, MonthlyBean data, int position) {
                holder.setText(R.id.item_monthly_title, data.getStyleNo());
                holder.setText(R.id.item_monthly_name, data.getStyleName());
                holder.setText(R.id.item_monthly_color, data.getColorName());
                holder.setText(R.id.item_monthly_size, data.getSizeName());
                holder.setText(R.id.item_monthly_bath, data.getBatch() + "");
                holder.setText(R.id.item_monthly_num, data.getQty1() + "");
                holder.setText(R.id.item_monthly_amount, data.getAmount() + "");
                holder.setText(R.id.item_monthly_num2, data.getQty2() + "");
                holder.setText(R.id.item_monthly_amount2, data.getAmount2() + "");
                holder.setText(R.id.item_monthly_num3, data.getQty3() + "");
                holder.setText(R.id.item_monthly_amount3, data.getAmount3() + "");
                holder.setText(R.id.item_monthly_num4, data.getQty4() + "");
                holder.setText(R.id.item_monthly_amount4, data.getAmount4() + "");
            }
        };
        binding.monthlyRv.setAdapter(adapter);
    }

    /**
     * 创建订单
     */
    private void getMonthly() {
        mkid = System.currentTimeMillis() + "" + (long) bean.getUserid();
        LogUtils.e("mkid " + mkid);
        HashMap<String, String> map = new HashMap<>();
        InventoryData data = new InventoryData();
        String date = binding.monthlyDate.getText().toString();
        data.setUserid(bean.getUserid());
        data.setDyear(date.split("-")[0]);
        data.setDmonth(date.split("-")[1]);
        data.setMkid(mkid);
        map.put(Constants.DATA, new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getMonthly(map), 0);
    }

    /**
     * 获取月报单明细
     */
    private void getMonthlyDetail(String mkid) {
        HashMap<String, String> map = new HashMap<>();
        InventoryData data = new InventoryData();
        data.setMkid(mkid);
        data.setUserid(bean.getUserid());
        map.put(Constants.DATA, new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getMonthlyDetail(map), 1);
    }

    @Override
    public void updateData(List<MonthlyBean> data, int flags) {
        switch (flags) {
            case 0:
                getMonthlyDetail(mkid);
                break;
            case 1:
                list = data;
                setAdapter(data);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.monthly_date:
                getDate();
                break;
            case R.id.monthly_statistics:
                if (TextUtils.isEmpty(binding.monthlyDate.getText())) {
                    showToast("请选择时间");
                    return;
                }
                getMonthly();
                break;
            case R.id.monthly_historical:
                Intent intent = new Intent(MonthlyReportActivity.this, HistoryStatementActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, Constants.SETRESULT);
                break;
            case R.id.monthly_export:
                new ExcelUtils(MonthlyReportActivity.this, list, "老头连锁店", binding.monthlyDate.getText().toString(), path);
                Intent share_intent = new Intent();
                share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                share_intent.setType("*/*");//设置分享内容的类型

                if (Build.VERSION.SDK_INT >= 24) {//添加分享内容
                    Uri apkUri = FileProvider.getUriForFile(getApplicationContext(), "com.sy.chainproject.fileprovider", new File(path));
                    share_intent.putExtra(Intent.EXTRA_STREAM, apkUri);
                }else{
                    share_intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));
                }
                startActivity(share_intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.SETRESULT_Q) {
            mkid = data.getStringExtra("mkid");
            binding.monthlyDate.setText(data.getStringExtra("date"));
            getMonthlyDetail(mkid);
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
            pvTime = new TimePickerBuilder(MonthlyReportActivity.this, (date, v) -> binding.monthlyDate.setText(getTime(date))).setType(new boolean[]{true, true, false, false, false, false}).setRangDate(startDate, endDate).setSubmitColor(getResources().getColor(R.color.colorAccent)).setDate(endDate).build();
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
    protected void onDestroy() {
        removePresenter(presenter);
        super.onDestroy();
    }

}
