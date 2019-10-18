package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.*;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityAddReturnGoodsBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.RetailPresenter;
import com.sy.chainproject.presenter.ReturnGoodsAddPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/8/9 9:32
 * @ author  zxcg
 * 新增退货单
 */
public class ReturnGoodsAddActivity extends BaseActivity implements BaseModelView.View<RetailBean>,BaseModelView.ViewOther<String>,BaseViewHolder.ViewOnclick  {
    private ActivityAddReturnGoodsBinding binding;
    private BasePresenter<RetailBean, RetailBean> presenter;
    private ReturnGoodsAddPresenter addPresenter;
    private BaseAdapter<RetailBean> adapter;
    private List<RetailBean> datas;
    private UserBean userBean;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_add_return_goods, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.add_return));
        binding = (ActivityAddReturnGoodsBinding) bindings;
        binding.returnAddReceipt.setOnClickListener(this);
        binding.returnAddCode.setOnClickListener(this);
        addPresenter = new ReturnGoodsAddPresenter(this);
        presenter = new RetailPresenter(this);
        userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        datas = new ArrayList<>();
    }

    private void setAdapter(List<RetailBean> data) {
        binding.returnAddDetailRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseAdapter<RetailBean>(this, R.layout.rv_item_return_detail, data,this) {
            @Override
            public void convert(BaseViewHolder holder, RetailBean data, int position) {
                holder.setText(R.id.item_return_detail, getString(R.string.ct_goods).concat(position + 1 + ""));
                holder.setText(R.id.item_rDetail_color, data.getColorName());
                holder.setText(R.id.item_rDetail_size, data.getSizeName());
                holder.setText(R.id.item_rDetail_styleName, data.getStyleName());
                holder.setEdText(R.id.item_rDetail_num, data.getQty() + "");
                holder.setOnclick(R.id.item_rDetail_update,position);
            }
        };
        binding.returnAddDetailRv.setAdapter(adapter);
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
     * 提交接口
     */
    private void getDataUpdate() {
        if(adapter==null)
            return;
        //确认修改
        for (int i = 0; i < adapter.getItemCount(); i++) {
            if(!datas.get(i).isState())
            {
                showToast(getString(R.string.return_info));
                return;
            }
        }
        HashMap<String, String> map = new HashMap<>();
        ReturnGoodsDetailData detailData = new ReturnGoodsDetailData();
        List<ReturnGoodsDetailData.DataBean> dataBeans = new ArrayList<>();
        detailData.setDyDate(getTime(new Date()));
        detailData.setRemark(binding.returnAddRemark.getText().toString());
        detailData.setStoreid("0");
        detailData.setSeasonid(datas.get(0).getSeasonid()+"");
        detailData.setBrandid(datas.get(0).getBrandid()+"");
        detailData.setUserid(userBean.getUserid());
        detailData.setCurrency("RMB");
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ReturnGoodsDetailData.DataBean bean = new ReturnGoodsDetailData.DataBean();
            bean.setColorid(datas.get(i).getColorid());
            bean.setBatch(1);
            bean.setSizeCode(datas.get(i).getSizeCode());
            bean.setPid("");
            bean.setQty(datas.get(i).getQty() + "");
            bean.setStyleid(datas.get(i).getStyleid()+"");
            bean.setSortid(datas.get(i).getSortid()+"");
            bean.setStockid(datas.get(i).getStockid());
            bean.setStoreid("0");
            dataBeans.add(bean);
        }
        detailData.setData(dataBeans);
        map.put(Constants.DATA, new Gson().toJson(detailData));
       // LogUtils.e( new GsonBuilder().create().toJson(detailData).replaceAll("null","\"\""));
        LogUtils.e( new Gson().toJson(detailData));
        addPresenter.getDataOther(RetrofitFactory.getInstance().API().Add_erp_backShopStoreInfo(map), 1);
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.return_add_code:
             startActivityForResult(new Intent(ReturnGoodsAddActivity.this,CaptureActivity.class),Constants.REQUESTCODE);
                break;
            case R.id.return_add_receipt:
                getDataUpdate();
                break;
        }
    }

    @Override
    public void clickView(View v, int position) {
        BaseViewHolder holder = (BaseViewHolder) binding.returnAddDetailRv.findViewHolderForAdapterPosition(position);
        TextView view = holder.getView(R.id.item_rDetail_update);
        if (view.getText().equals(getString(R.string.ct_update))) {
            view.setText(getString(R.string.ok));
            view.setTextColor(getResources().getColor(R.color.colorAccent));
            datas.get(position).setState(false);
            holder.getView(R.id.item_rDetail_num).setEnabled(true);
        } else {
            TextView view2 = holder.getView(R.id.item_rDetail_num);
            view.setText(getString(R.string.ct_update));
            view.setTextColor(getResources().getColor(R.color.bg_title_bar));
            datas.get(position).setState(true);
            RetailBean bean = datas.get(position);
            if (!TextUtils.isEmpty(view2.getText().toString())) bean.setQty(Integer.valueOf(view2.getText().toString()));
            holder.getView(R.id.item_rDetail_num).setEnabled(false);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.SETRESULT) {
            String str = data.getStringExtra("data");
            getRetailData(str);
        }
    }

    @Override
    public void updateData(RetailBean data, int flags) {
        for(int i=0;i<datas.size();i++){
            if(datas.get(i).getStockid().equals(data.getStockid())){
                datas.get(i).setQty(datas.get(i).getQty()+1);
                setAdapter(datas);
                return;
            }
        }
        datas.add(data);
        setAdapter(datas);
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }



    @Override
    public void updateOther(String data, int flags) {
        showToast(data);
        setResult(Constants.SETRESULT);
        finish();
    }

    @Override
    public void onFailureOther(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
        if(addPresenter!=null)
            addPresenter=null;
    }
}
