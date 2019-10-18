package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.HistoryBean;
import com.sy.chainproject.bean.HistoryData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityHistoryStatementBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.HistoryPresenter;
import com.sy.chainproject.utils.SharedPreferencesUtils;
import com.sy.chainproject.view.DividerDecoration;

import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/7/16 15:13
 * @ author  zxcg
 * 月报及报表盘点历史
 */
public class HistoryStatementActivity extends BaseActivity implements BaseViewHolder.ViewOnclick, BaseModelView.View<List<HistoryBean>> {
    private ActivityHistoryStatementBinding binding;
    private BasePresenter<HistoryBean, List<HistoryBean>> presenter;
    private List<HistoryBean> data;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_history_statement, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.historical_data));
        binding = (ActivityHistoryStatementBinding) bindings;
        binding.historyRv.setLayoutManager(new LinearLayoutManager(this));
        binding.historyRv.addItemDecoration(new DividerDecoration(this));
        getInventoryHistoryList();
    }

    /**
     * 设置适配器
     */
    private void setAdapter(List<HistoryBean> list) {
        binding.historyRv.setAdapter(new BaseAdapter<HistoryBean>(this, R.layout.rv_item_history, list, this) {
            @Override
            public void convert(BaseViewHolder holder, HistoryBean data, int position) {
                holder.setText(R.id.item_history_mkid, data.getMkid() + "");
                holder.setText(R.id.item_history_year, data.getDyear() + "");
                holder.setText(R.id.item_history_month, data.getDmonth() + "");
                holder.setText(R.id.item_history_time, data.getDtime());
                holder.setText(R.id.item_history_operator, "");
                holder.setItemViewOnClick(position);
            }
        });
    }

    /**
     * 请求历史数据
     */
    private void getInventoryHistoryList() {
        UserBean bean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        presenter = new HistoryPresenter(this);
        HashMap<String, String> map = new HashMap<>();
        HistoryData data = new HistoryData();
        data.setUserid(bean.getUserid());
        data.setStartPage(1);
        data.setStep(100);
        data.setTypeid(getIntent().getIntExtra("type", 0));
        map.put(Constants.DATA, new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getInventoryHistoryList(map), 0);
    }

    @Override
    public void clickView(View v, int position) {
        Intent intent = new Intent();
        intent.putExtra("mkid",data.get(position).getMkid()+"");
        intent.putExtra("date",data.get(position).getDyear()+"-"+data.get(position).getDmonth());
        setResult(Constants.SETRESULT_Q,intent);
        finish();
    }

    @Override
    public void updateData(List<HistoryBean> data, int flags) {
        this.data = data;
        setAdapter(data);
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
