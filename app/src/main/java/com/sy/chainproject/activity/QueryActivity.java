package com.sy.chainproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.bean.QueryBean;
import com.sy.chainproject.bean.QueryData;
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

public class QueryActivity extends Activity implements View.OnClickListener, BaseViewHolder.ViewOnclick, BaseModelView.View<List<QueryBean>> {

    private ActivityQueryBinding binding;
    private List<String> list;
    private QueryPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_query);
        binding.queryBack.setOnClickListener(this);
        binding.queryBt.setOnClickListener(this);
        binding.queryCancel.setOnClickListener(this);
        getQuery();
       list = new ArrayList<>();
       /*  for (int i = 0; i < 15; i++) {
            list.add(i + "  数据");
        }*/
       if(list.size()==0){
           list.add(getString(R.string.no_data));
       }
        binding.queryRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.queryRv.setAdapter(new BaseAdapter<String>(this, R.layout.rv_item_sp, list, this) {
            @Override
            public void convert(BaseViewHolder holder, String data, int position) {
                holder.setText(R.id.item_sp, data);
                holder.setOnclick(R.id.item_sp, position);
            }
        });
    }

    private void getQuery(){
        String styleNo = binding.queryQuery.getText().toString();
        if(TextUtils.isEmpty(styleNo))
            return;
        presenter = new QueryPresenter(this);
        HashMap<String,String> map = new HashMap<>();
        QueryData data= new QueryData();
        UserBean bean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        data.setStep(30);
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
                startActivity(new Intent(QueryActivity.this,QueryResultsActivity.class));
                break;
        }
    }

    @Override
    public void clickView(View v, int position) {
        TextView view = (TextView) v;
        String string = view.getText().toString();
        if(getString(R.string.no_data).equals(string))
            return;
        binding.queryQuery.setText(string);
    }

    @Override
    public void updateData(List<QueryBean> data, int flags) {

    }

    @Override
    public void onFailure(String e, int flags) {

    }

    @Override
    protected void onDestroy() {
        if(presenter!=null)
            presenter.detach();
        super.onDestroy();
    }
}
