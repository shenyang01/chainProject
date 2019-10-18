package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.*;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityReceivingDetailBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.ReasonPresenter;
import com.sy.chainproject.presenter.ReceivingDetailPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;
import com.sy.chainproject.utils.TypedValueUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/5/5 14:42
 * @ author  zxcg
 * 收货详情
 */

public class ReceivingDetailActivity extends BaseActivity implements BaseModelView.View<List<ReceivingDetailBean>>, BaseViewHolder.ViewOnclick, BaseModelView.ViewOther<List<ReasonBean>> {
    private ActivityReceivingDetailBinding binding;
    private ReceivingDetailPresenter presenter;
    private ReasonPresenter reasonPresenter;
    private BaseAdapter<ReceivingDetailBean> adapter;
    private List<ReceivingDetailBean> datas;
    private UserBean userData;
    private String storeid;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_receiving_detail, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setBaseBask(getString(R.string.black));
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseTitle(getString(R.string.order_details));
        binding = (ActivityReceivingDetailBinding) bindings;
       // binding.cancelReceipt.setOnClickListener(this);
        binding.confirmReceipt.setOnClickListener(this);
        userData = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        storeid = getIntent().getStringExtra("storeid"); //outstoreid
        getReceivingDetailType();
        getReceivingGoods();
        binding.goodsDetails.setLayoutManager(new LinearLayoutManager(this));
    }


    /***
     * 获取订单类别
     */
    private void getReceivingDetailType() {
        reasonPresenter = new ReasonPresenter(this);
        reasonPresenter.getDataOther(RetrofitFactory.getInstance().API().getReceivingDetailType(userData.getUserid()), 0);
    }

    /**
     * 获取订单信息
     */
    private void getReceivingGoods() {
        presenter = new ReceivingDetailPresenter(this);
        ReceivingNoteData data = new ReceivingNoteData();
        data.setUserid(userData.getUserid() + "");
        data.setStoreid(storeid);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getReceivingDetail(hashMap), 0);
    }

    /**
     * 收货修改后提交
     */
    private void getReceivingUpdate() {
        if (adapter == null) return;
        String name = binding.receivingName.getText().toString();
        if(TextUtils.isEmpty(name)){
            showToast(getString(R.string.name_null));
            return;
        }
        //确认修改
        for (int i = 0; i < adapter.getItemCount(); i++) {
            if(!datas.get(i).isState())
            {
                showToast(getString(R.string.return_info));
                return;
            }
        }
        HashMap<String, String> map = new HashMap<>();
        ReceivingUpdateData data = new ReceivingUpdateData();
        List<ReceivingUpdateData.DataBean> beans = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ReceivingUpdateData.DataBean bean = new ReceivingUpdateData.DataBean();
            bean.setColorid(datas.get(i).getColorid());
            bean.setQty(datas.get(i).getQty() + "");
            bean.setMkey(datas.get(i).getMkey());
            bean.setPid(datas.get(i).getPid());
            bean.setSortid(datas.get(i).getSortid());
            bean.setFail(datas.get(i).getQty_no());
            beans.add(bean);
        }
        data.setData(beans);
        data.setSize(adapter.getItemCount());
        data.setRemark(binding.rgOriginal.getText().toString());
        data.setOutStoreid(storeid);
        data.setTypeStore(binding.receivingType.getSelectedItemPosition());
        data.setUserid(userData.getUserid());
        data.setdPerson(name);
        map.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));

        presenter.getData2(RetrofitFactory.getInstance().API().getReceivingDetailUpdate(map), 1);
    }

    private void setSpinnerAdapter(List<String> data) {
        if (data == null) return;
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.rv_item_sp, R.id.item_sp, data);
        binding.receivingType.setDropDownVerticalOffset(TypedValueUtils.dpTopx(this, 40));
        binding.receivingType.setAdapter(adapter);
    }

    private void setRvAdapter(List<ReceivingDetailBean> data) {
        this.datas = data;
        adapter = new BaseAdapter<ReceivingDetailBean>(this, R.layout.item_receiving_goods, datas, this) {
            @Override
            public void convert(BaseViewHolder holder, ReceivingDetailBean data, int position) {
                holder.setText(R.id.item_goods_detail, getString(R.string.ct_goods).concat(position + 1 + ""));
                holder.setOnclick(R.id.item_goods_update, position);
                holder.setText(R.id.item_goods_size, data.getSizeName());
                holder.setText(R.id.item_goods_color, data.getColorName());
                holder.setEdText(R.id.item_goods_num, data.getQty() + "");
                holder.setEdText(R.id.item_goods_num_no, data.getQty_no() + "");
            }
        };
        binding.goodsDetails.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
           /* case R.id.cancel_receipt:  //取消收货
                break;*/
            case R.id.confirm_receipt: // 确认收货
                getReceivingUpdate();
                break;
        }
    }

    @Override
    public void updateData(List<ReceivingDetailBean> data, int flags) {
        if (flags == 0) {
            setRvAdapter(data);
        } else {
            setResult(Constants.SETRESULT);
            showToast("提交成功");
            finish();
        }
    }

    @Override
    public void onFailure(String e, int flags) {
    }

    @Override
    public void clickView(View v, int position) {
        BaseViewHolder holder = (BaseViewHolder) binding.goodsDetails.findViewHolderForAdapterPosition(position);
        TextView view = holder.getView(R.id.item_goods_update);
        if (view.getText().equals(getString(R.string.ct_update))) {
            view.setText(getString(R.string.ok));
            view.setTextColor(getResources().getColor(R.color.colorAccent));
            datas.get(position).setState(false);
            holder.getView(R.id.item_goods_num).setEnabled(true);
            holder.getView(R.id.item_goods_num_no).setEnabled(true);
        } else {
            TextView view2 = holder.getView(R.id.item_goods_num);
            TextView view3 = holder.getView(R.id.item_goods_num_no);
            view.setText(getString(R.string.ct_update));
            view.setTextColor(getResources().getColor(R.color.bg_title_bar));
            datas.get(position).setState(true);
            ReceivingDetailBean bean = datas.get(position);
            bean.setQty(Integer.valueOf(view2.getText().toString()));
            bean.setQty_no(view3.getText().toString());
            holder.getView(R.id.item_goods_num).setEnabled(false);
            holder.getView(R.id.item_goods_num_no).setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        removePresenter(presenter);
        if (reasonPresenter != null) reasonPresenter = null;
        super.onDestroy();
    }

    @Override
    public void updateOther(List<ReasonBean> data, int flags) {
        List<String> list = new ArrayList<>();
        for (ReasonBean bean : data) {
            list.add(bean.getCnName());
        }
        setSpinnerAdapter(list);
    }

    @Override
    public void onFailureOther(String e, int flags) {

    }
}
