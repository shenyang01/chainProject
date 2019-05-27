package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityQueryResultsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @ data  2019/5/7 15:05
 * @ author  zxcg
 * 查询结果
 */

public class QueryResultsActivity extends BaseActivity implements BaseViewHolder.ViewOnclick {
    private ActivityQueryResultsBinding binding;
    private List<String> list;
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_query_results,null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.query_result));
        setColor(getResources().getColor(R.color.bg_title_bar));
        binding= (ActivityQueryResultsBinding) bindings;
        list = new ArrayList<>();
        list.add("111");
        list.add("111");
        list.add("111");
        list.add("111");
        binding.queryResults.setLayoutManager(new LinearLayoutManager(this));
        binding.queryResults.setAdapter(new BaseAdapter<String>(this,R.layout.rv_item_results,list,this) {
            @Override
            public void convert(BaseViewHolder holder, String data, int position) {
                holder.setText(R.id.item_results_store,data);
                holder.setText(R.id.item_results_title,data);
                holder.setText(R.id.item_results_color,data);
                holder.setText(R.id.item_results_size,data);
                holder.setText(R.id.item_results_stock,data);
                holder.setImagerView(R.id.item_results_image,R.mipmap.collect);
                holder.setItemViewOnClick(position);
            }
        });
    }

    @Override
    public void clickView(View v, int position) {
        startActivity(new Intent(QueryResultsActivity.this, ImageViewPagerActivity.class));
    }
}
