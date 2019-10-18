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
import com.sy.chainproject.bean.ReturnBean;
import com.sy.chainproject.bean.ReturnData;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityReturnGoodsBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.ReturnGoodsPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/5/8 16:49
 * @ author  zxcg
 * 退货页面
 */
public class ReturnGoodsActivity extends BaseActivity implements BaseModelView.View<List<ReturnBean>>, BaseViewHolder.ViewOnclick {
    private ActivityReturnGoodsBinding binding;
    private TimePickerView pvTime;
    private BasePresenter<ReturnBean, List<ReturnBean>> presenter;
    private BaseAdapter<ReturnBean> adapter;
    private List<ReturnBean> list;
    private int position;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_return_goods, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.return_goods));
        setBaseRight(getString(R.string.add_return));
        binding = (ActivityReturnGoodsBinding) bindings;
        binding.returnDate.setText(getTime(new Date()));
        binding.returnDate.setOnClickListener(this);
        binding.returnQuery.setOnClickListener(this);
        list = new ArrayList<>();
        getData(0);
    }

    private void setAdapter(List<ReturnBean> list) {
        binding.returnGoodsIv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseAdapter<ReturnBean>(this, R.layout.rv_item_return, list, this) {
            @Override
            public void convert(BaseViewHolder holder, ReturnBean data, int position) {
                holder.setText(R.id.item_return_storeid, data.getStoreNum() + "");
                holder.setText(R.id.item_return_time, data.getDtime());
                holder.setText(R.id.item_return_styleNo, data.getStyleNo());
                holder.setText(R.id.item_return_styleName, data.getStyleName());
                holder.setText(R.id.item_return_num, data.getTotalQty() + "");
                holder.setText(R.id.item_return_shop, data.getShopName());
                holder.setText(R.id.item_return_received, data.getPAmount() + "");
                holder.setText(R.id.item_return_totalAmount, data.getTotalAmount() + "");
                holder.setItemViewOnClick(position);
            }
        };
        binding.returnGoodsIv.setAdapter(adapter);
    }

    /**
     * 数据请求
     */
    private void getData(int flag) {
        presenter = new ReturnGoodsPresenter(this);
        String styleNo = binding.returnStyleNo.getText().toString();
        String date = binding.returnDate.getText().toString();
        ReturnData data = new ReturnData();
        HashMap<String, String> map = new HashMap<>();
        data.setBackStatus(1);
        data.setD1(date.substring(0, 8) + "01");
        data.setD2(date);
        data.setStyleNo(styleNo);
        data.setUserid(SharedPreferencesUtils.getUserdata(Constants.USERDATA).getUserid());
        map.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().get_erp_InShopStoreInfo(map), flag);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.return_date:
                getDate();
                break;
            case R.id.return_query:
                getData(1);
                break;
            case R.id.base_right:
                Intent intent = new Intent(ReturnGoodsActivity.this, ReturnGoodsAddActivity.class);
                startActivityForResult(intent, Constants.REQUESTCODE);
                break;
        }
    }

    @Override
    public void updateData(List<ReturnBean> data, int flags) {
        list.clear();
        list.addAll(data);
        switch (flags) {
            case 0:
                setAdapter(list);
                break;
            case 1:
                adapter.notifyDataSetChanged();
                break;
            case 2:
                adapter.notifyItemChanged(position);
                break;
        }
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
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
            pvTime = new TimePickerBuilder(ReturnGoodsActivity.this, (date, v) -> {
                binding.returnDate.setText(getTime(date));
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
    public void clickView(View v, int position) {
        this.position = position;
        Intent intent = new Intent(ReturnGoodsActivity.this, ReturnGoodsDetailActivity.class);
        intent.putExtra("storeid", list.get(position).getStoreid() + "");
        intent.putExtra("seasonid", list.get(position).getSeasonid() + "");
        intent.putExtra("brandid", list.get(position).getBrandid() + "");
        startActivityForResult(intent, Constants.REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.SETRESULT) {
            getData(2);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }
}
