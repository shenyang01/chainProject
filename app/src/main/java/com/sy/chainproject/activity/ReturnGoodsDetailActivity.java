package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.ReturnData;
import com.sy.chainproject.bean.ReturnGoodsDetailBean;
import com.sy.chainproject.bean.ReturnGoodsDetailData;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityReturnGoodsDetailBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.ReturnGoodsAddPresenter;
import com.sy.chainproject.presenter.ReturnGoodsDetailPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/8/7 15:40
 * @ author  zxcg
 * 退货单明细
 */
public class ReturnGoodsDetailActivity extends BaseActivity implements BaseModelView.View<List<ReturnGoodsDetailBean>>, BaseModelView.ViewOther<String>, BaseViewHolder.ViewOnclick {

    private ActivityReturnGoodsDetailBinding binding;
    private BasePresenter<ReturnGoodsDetailBean, List<ReturnGoodsDetailBean>> presenter;
    private ReturnGoodsAddPresenter addPresenter;
    private BaseAdapter<ReturnGoodsDetailBean> adapter;
    private List<ReturnGoodsDetailBean> datas;
    private String storeid, seasonid, brandid;
    private int userid;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_return_goods_detail, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.return_goods_detail));
        binding = (ActivityReturnGoodsDetailBinding) bindings;
        binding.returnReceipt.setOnClickListener(this);
        presenter = new ReturnGoodsDetailPresenter(this);
        storeid = getIntent().getStringExtra("storeid");
        seasonid = getIntent().getStringExtra("seasonid");
        brandid = getIntent().getStringExtra("brandid");
        userid = SharedPreferencesUtils.getUserdata(Constants.USERDATA).getUserid();
        getData();
    }

    private void setAdapter(List<ReturnGoodsDetailBean> data) {
        binding.returnDetailRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseAdapter<ReturnGoodsDetailBean>(this, R.layout.rv_item_return_detail, data, this) {
            @Override
            public void convert(BaseViewHolder holder, ReturnGoodsDetailBean data, int position) {
                holder.setText(R.id.item_return_detail, getString(R.string.ct_goods).concat(position + 1 + ""));
                holder.setText(R.id.item_rDetail_color, data.getColorName());
                holder.setText(R.id.item_rDetail_size, data.getSizeName());
                holder.setText(R.id.item_rDetail_styleName, data.getStyleName());
                holder.setEdText(R.id.item_rDetail_num, data.getQty() + "");
                holder.setOnclick(R.id.item_rDetail_update, position);
            }
        };
        binding.returnDetailRv.setAdapter(adapter);
    }

    /**
     * 请求明细接口
     */
    private void getData() {
        HashMap<String, String> map = new HashMap<>();
        ReturnData data = new ReturnData();
        data.setUserid(userid);
        data.setStoreid(storeid);
        map.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data) + "  " + storeid);
        presenter.getData(RetrofitFactory.getInstance().API().get_erp_InShopStoreDetail(map), 0);
    }

    /**
     * 提交接口
     */
    private void getDataUpdate() {
        if (adapter == null) return;
        //确认修改
        for (int i = 0; i < adapter.getItemCount(); i++) {
            if(!datas.get(i).isState())
            {
                showToast(getString(R.string.return_info));
                return;
            }
        }
        addPresenter = new ReturnGoodsAddPresenter(this);
        HashMap<String, String> map = new HashMap<>();
        ReturnGoodsDetailData detailData = new ReturnGoodsDetailData();
        List<ReturnGoodsDetailData.DataBean> dataBeans = new ArrayList<>();
        detailData.setDyDate(getTime(new Date()));
        detailData.setRemark(binding.returnRemark.getText().toString());
        detailData.setStoreid(storeid);
        detailData.setSeasonid(seasonid);
        detailData.setBrandid(brandid);
        detailData.setUserid(userid);
        LogUtils.e(seasonid + "  " + storeid + "  " + userid + "  " + brandid);
        detailData.setCurrency("RMB");
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ReturnGoodsDetailData.DataBean bean = new ReturnGoodsDetailData.DataBean();
            bean.setColorid(datas.get(i).getColorid());
            bean.setBatch(1);
            bean.setSizeCode(datas.get(i).getSizeCode());
            bean.setPid(datas.get(i).getPid());
            bean.setQty(datas.get(i).getQty() + "");
            bean.setStyleid(datas.get(i).getStyleid());
            bean.setSortid(datas.get(i).getSortid());
            bean.setStockid(datas.get(i).getStoreid());
            bean.setStoreid(datas.get(i).getSortid());
            dataBeans.add(bean);
        }
        detailData.setData(dataBeans);
        map.put(Constants.DATA, new GsonBuilder().create().toJson(detailData).replaceAll("null", "\"\""));

        LogUtils.e(new GsonBuilder().create().toJson(detailData).replaceAll("null", "\"\""));
        addPresenter.getDataOther(RetrofitFactory.getInstance().API().Add_erp_backShopStoreInfo(map), 1);
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    @Override
    public void updateData(List<ReturnGoodsDetailBean> data, int flags) {
        datas = data;
        setAdapter(data);

    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.return_receipt) getDataUpdate();
    }

    @Override
    public void clickView(View v, int position) {
        BaseViewHolder holder = (BaseViewHolder) binding.returnDetailRv.findViewHolderForAdapterPosition(position);
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
            ReturnGoodsDetailBean bean = datas.get(position);
            if (!TextUtils.isEmpty(view2.getText().toString())) bean.setQty(Integer.valueOf(view2.getText().toString()));
            holder.getView(R.id.item_rDetail_num).setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
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
}
