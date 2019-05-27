package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.ReceivingDetailBean;
import com.sy.chainproject.bean.ReceivingNoteData;
import com.sy.chainproject.bean.ReceivingUpdateData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityReceivingDetailBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.ReceivingDetailPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/5/5 14:42
 * @ author  zxcg
 * 收货详情
 */

public class ReceivingDetailActivity extends BaseActivity implements BaseModelView.View<List<ReceivingDetailBean>>, BaseViewHolder.ViewOnclick {
    private ActivityReceivingDetailBinding binding;
    private ReceivingDetailPresenter  presenter;
    private BaseAdapter<ReceivingDetailBean> adapter;
    private String storeid;
    private List<ReceivingDetailBean> datas;

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
        binding.cancelReceipt.setOnClickListener(this);
        binding.confirmReceipt.setOnClickListener(this);


        getReceivingGoods();
        binding.goodsDetails.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 获取订单信息
     */
    private void getReceivingGoods() {
        storeid = getIntent().getStringExtra("storeid");
        presenter = new ReceivingDetailPresenter(this);
        UserBean userData = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        ReceivingNoteData data = new ReceivingNoteData();
        data.setUserid(userData.getUserid() + "");
        data.setStoreid(storeid);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.DATA, new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getReceivingDetail(hashMap), 0);
    }

    /**
     *  收货修改后提交
     */
    private void getReceivingUpdata() {
        HashMap<String,String> map = new HashMap<>();
        ReceivingUpdateData data = new ReceivingUpdateData();
        List<ReceivingUpdateData.DataBean> beans = new ArrayList<>();
        for (int i =0;i<adapter.getItemCount();i++){
            ReceivingUpdateData.DataBean  bean = new  ReceivingUpdateData.DataBean();
            bean.setColorid(datas.get(i).getColorid());
            bean.setQty(datas.get(i).getQty()+"");
            bean.setMkey(datas.get(i).getMkey());
            bean.setPid(datas.get(i).getPid());
            bean.setSortid(datas.get(i).getSortid());
            bean.setFail("1");
            beans.add(bean);
        }
        data.setData(beans);
        data.setSize(adapter.getItemCount());
        data.setRemark(binding.rgOriginal.getText().toString());
        data.setStoreid(datas.get(0).getStoreid());
       // data.setIMEI(Constants.IMEI);
        data.setIMEI("123321");
        map.put(Constants.DATA,new Gson().toJson(data));
       // LogUtils.e(new Gson().toJson(data));

        presenter.getData2(RetrofitFactory.getInstance().API().getReceivingDetailUpdata(map),1);
    }
    private void setRvAdapter(List<ReceivingDetailBean> data) {
        this.datas=data;
        adapter = new BaseAdapter<ReceivingDetailBean>(this, R.layout.item_receiving_goods, datas, this) {
            @Override
            public void convert(BaseViewHolder holder, ReceivingDetailBean data, int position) {
                holder.setText(R.id.item_goods_detail, getString(R.string.ct_goods).concat(position + 1 + ""));
                holder.setOnclick(R.id.item_goods_update, position);
                holder.setText(R.id.item_goods_size, data.getSizeName());
                holder.setText(R.id.item_goods_color, data.getColorName());
                holder.setText(R.id.item_goods_num, data.getQty() + "");
            }
        };
        binding.goodsDetails.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.cancel_receipt:  //取消收货
                break;
            case R.id.confirm_receipt: // 确认收货
                getReceivingUpdata();
                break;
        }
    }

    @Override
    public void updateData(List<ReceivingDetailBean> data, int flags) {
        if(flags==0){
            setRvAdapter(data);
        }else {
            showToast("提交成功");
        }
    }



    @Override
    public void onFailure(String e, int flags) {
    }

    @Override
    public void clickView(View v, int position) {
        BaseViewHolder holder= (BaseViewHolder) binding.goodsDetails.findViewHolderForAdapterPosition(position);
        TextView view =holder.getView(R.id.item_goods_update);
        if(view.getText().equals(getString(R.string.ct_update))){
            view.setText(getString(R.string.ok));
            holder.getView(R.id.item_goods_num).setEnabled(true);
            holder.getView(R.id.item_goods_num_no).setEnabled(true);
        }else{
            TextView view2 =holder.getView(R.id.item_goods_num);
            TextView view3 =holder.getView(R.id.item_goods_num_no);
            view.setText(getString(R.string.ct_update));
            ReceivingDetailBean bean =datas.get(position);
            bean.setQty(Integer.valueOf(view2.getText().toString()));
            bean.setQty_no(view3.getText().toString());
            holder.getView(R.id.item_goods_num).setEnabled(false);
            holder.getView(R.id.item_goods_num_no).setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        removePresenter(presenter);
        super.onDestroy();
    }

}
