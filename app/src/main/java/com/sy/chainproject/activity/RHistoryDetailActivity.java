package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.*;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityRhistoryDetailBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.RetailDetailPresenter;
import com.sy.chainproject.presenter.ReasonPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/7/4 11:25
 * @ author  zxcg
 * 零售订单明细
 */
public class RHistoryDetailActivity extends BaseActivity implements BaseModelView.View<List<RetailBean>>, BaseModelView.ViewOther<List<ReasonBean>>, RadioGroup.OnCheckedChangeListener {
    private ActivityRhistoryDetailBinding binding;
    private String posid;
    private BasePresenter<RetailBean, List<RetailBean>> presenter;
    private ReasonPresenter otherPresenter;
    private List<RetailBean> list;  //订单明细数据源
    private UserBean userBean;
    private String index;
    private List<ReasonBean> data;
    private BottomSheetDialog dialog;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_rhistory_detail, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (ActivityRhistoryDetailBinding) bindings;
        posid = getIntent().getStringExtra("posid");
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.details));
        binding.rhistoryDetailPosid.setText(getString(R.string.order_number).concat(posid));
        binding.rhistoryDetailReceivable.setText(getIntent().getStringExtra("data"));
        binding.rhistoryDetailReceived.setText(getIntent().getStringExtra("data2"));
        boolean state = getIntent().getBooleanExtra("state", false);
        if (state) {
            binding.rhistoryDetailRetreat.setText(getString(R.string.retreat_2));
        } else {
            binding.rhistoryDetailRetreat.setText(getString(R.string.retreat));
            binding.rhistoryDetailRetreat.setOnClickListener(this);
        }
        presenter = new RetailDetailPresenter(this);
        getPosHistoryDetail();
    }

    private void setAdapter(List<RetailBean> list) {
        binding.rhistoryDetailRv.setLayoutManager(new LinearLayoutManager(this));
        binding.rhistoryDetailRv.setAdapter(new BaseAdapter<RetailBean>(this, R.layout.rv_item_retail, list) {
            @Override
            public void convert(BaseViewHolder holder, RetailBean data, int position) {
                holder.setText(R.id.item_retail_name, data.getStyleName());
                holder.setText(R.id.item_retail_color, data.getColorName());
                holder.setText(R.id.item_retail_size, data.getSizeName());
                holder.setText(R.id.item_retail_num, data.getQty() + "");
                holder.setText(R.id.item_retail_price, data.getSellPrice());
            }
        });
    }

    /**
     * 订单数据请求
     */
    private void getPosHistoryDetail() {
        userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        HashMap<String, String> map = new HashMap<>();
        RetailHistoryData data = new RetailHistoryData();
        data.setFlag(1);
        data.setPosid(posid);
        data.setUserid(userBean.getUserid() + "");
        map.put(Constants.DATA, new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getPosHistoryDetail(map), 0);
    }

    /**
     * 退单数据请求
     */
    private void getChargeback(String checkedId) {
        //退款单posid生成
        HashMap<String, String> map = new HashMap<>();
        RetailPOSData data = new RetailPOSData();
        RetailPOSData.DataBean bean;
        data.setUserid(userBean.getUserid()+"");
        data.setPosid(posid);
        data.setCurrency("RMB");
        data.setBackStatus(1);
        data.setFlag(1);
        data.setRemark(checkedId);

        List<RetailPOSData.DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            bean = new RetailPOSData.DataBean();
            bean.setPosid(posid);
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
        LogUtils.e("tag",new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getPosData(map), 2);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rhistoryDetail_retreat:  //退单原因请求
                if (otherPresenter == null) otherPresenter = new ReasonPresenter(this);
                otherPresenter.getDataOther(RetrofitFactory.getInstance().API().getPosHistoryReason(userBean.getUserid()), 1);
                break;
            case R.id.dialog_detail_submission:
                getChargeback(index);
                break;
        }
    }

    /**
     * @param data 退货原因弹出框
     */
    public void showDialog(List<ReasonBean> data) {
        dialog = new BottomSheetDialog(RHistoryDetailActivity.this);
        View dialogView = LayoutInflater.from(RHistoryDetailActivity.this).inflate(R.layout.dialog_rhistory_detail, null);
        RadioGroup group = dialogView.findViewById(R.id.dialog_detail_rg);
        dialogView.findViewById(R.id.dialog_detail_submission).setOnClickListener(this);
        RadioButton button;
        for (int i = 0; i < data.size(); i++) {
            button = new RadioButton(this);
            button.setText(data.get(i).getCnName());
            button.setId(i);
            group.addView(button, i, new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        button = (RadioButton) group.getChildAt(0);
        button.setChecked(true);
        group.setOnCheckedChangeListener(this);
        dialog.setContentView(dialogView);
        dialog.show();
    }

    @Override
    public void updateData(List<RetailBean> data, int flags) {
        if (flags == 0) {
            list = data;
            setAdapter(data);
        } else if (flags == 2) {
            dialog.dismiss();
            binding.rhistoryDetailRetreat.setText(getString(R.string.retreat_2));
            setResult(Constants.SETRESULT);
            showToast("提交成功");
        }
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
        if (flags == 2 && dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    @Override
    public void updateOther(List<ReasonBean> data, int flags) {
        this.data = data;
        showDialog(data);
    }

    @Override
    public void onFailureOther(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
        if (otherPresenter != null) otherPresenter = null;

    }

    /**
     * @param checkedId 从  0  开始
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        index = data.get(checkedId).getCnName();
    }
}
