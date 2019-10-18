package com.sy.chainproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.bean.QueryData;
import com.sy.chainproject.bean.RetailBean;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityQueryBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.QueryPresenter;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/5/7 10:52
 * @ author  zxcg
 * 查询  根据款号和订单号
 */

public class QueryActivity extends Activity implements View.OnClickListener, BaseViewHolder.ViewOnclick, BaseModelView.View<List<RetailBean>>{

private ActivityQueryBinding binding;
    private QueryPresenter presenter;
    private List<RetailBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_query);
        binding.queryBack.setOnClickListener(this);
        binding.queryBt.setOnClickListener(this);
        binding.queryCancel.setOnClickListener(this);
    }
    private void setAdapter(List<RetailBean> data) {
        binding.queryResults.setLayoutManager(new LinearLayoutManager(this));
        binding.queryResults.setAdapter(new BaseAdapter<RetailBean>(this,R.layout.rv_item_results,data,this) {
            @Override
            public void convert(BaseViewHolder holder, RetailBean data, int position) {
                holder.setText(R.id.item_results_barCode,data.getBarCode());
                holder.setText(R.id.item_results_shop,data.getShopName());
                holder.setText(R.id.item_results_name,data.getStyleName());
                holder.setText(R.id.item_results_color,data.getColorName());
                holder.setText(R.id.item_results_size,data.getSizeName());
                holder.setText(R.id.item_results_stock,data.getQty()+"");
                // holder.setImagerView(R.id.item_results_image,R.mipmap.collect);
                Glide.with(QueryActivity.this).load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1558339950&di=ec7ea1a3602fda91e07910d1ba3a5682&src=http://pic36.nipic.com/20131110/14741728_170149392100_2.jpg").into((ImageView) holder.getView(R.id.item_results_image));
                holder.setOnclick(R.id.item_results_image,position);
                holder.setItemViewOnClick(position);
            }
        });
    }
    private void getQuery(String styleNo){
        if(TextUtils.isEmpty(styleNo))
            return;
        presenter = new QueryPresenter(this);
        HashMap<String,String> map = new HashMap<>();
        QueryData data= new QueryData();
        UserBean bean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        data.setFlag(Constants.FLAG);
        data.setStep(30);
        data.setShopid(0); //0 查询全部  1 查询本店
        data.setUserid(bean.getUserid()+"");
        data.setDatabaseName(bean.getDatabaseName());
        data.setStyleNo(styleNo);
        map.put(Constants.DATA,new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getGoodsInfo(map),0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query_back:
                finish();
                break;
            case R.id.query_cancel:
                binding.queryQuery.setText("");
                break;
            case R.id.query_bt:
                String styleNo = binding.queryQuery.getText().toString();
                if (TextUtils.isEmpty(styleNo))
                    return;
                getQuery(styleNo);
                break;
        }
    }
    @Override
    public void updateData(List<RetailBean> data, int flags) {
        list = data;
        setAdapter(data);
    }

    @Override
    public void onFailure(String e, int flags) {
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
        setAdapter(new ArrayList<>());
    }

    @Override
    public void clickView(View v, int position) {
        if(v.getId()==R.id.item_results_image)
            startActivity(new Intent(QueryActivity.this, ImageViewPagerActivity.class));
        else{
            Intent intent = new Intent();
            intent.putExtra("bean",list.get(position));
            setResult(Constants.SETRESULT_Q,intent);
        }
    }

    @Override
    protected void onDestroy() {
        if(presenter!=null)
            presenter.detach();
        super.onDestroy();
    }
}
