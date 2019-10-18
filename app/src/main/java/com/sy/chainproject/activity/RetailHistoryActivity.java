package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.RetailHistoryBean;
import com.sy.chainproject.bean.RetailHistoryData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityRetailHistoryBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.RetailHistoryPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/7/3 15:17
 * @ author  zxcg
 * 零售历史单据
 */
public class RetailHistoryActivity extends BaseActivity implements BaseModelView.View<List<RetailHistoryBean>>, BaseViewHolder.ViewOnclick {
    private BasePresenter<RetailHistoryBean, List<RetailHistoryBean>> presenter;
    private ActivityRetailHistoryBinding binding;
    private List<RetailHistoryBean> list;
    private int position;
    private BaseAdapter baseAdapter;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_retail_history, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.history));
        binding = (ActivityRetailHistoryBinding) bindings;
        getPosHistory();
    }

    /**
     * 历史零售单查询
     */
    private void getPosHistory() {
        list = new ArrayList<>();
        presenter = new RetailHistoryPresenter(this);
        UserBean bean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        HashMap<String, String> map = new HashMap<>();
        RetailHistoryData data = new RetailHistoryData();
        data.setFlag(1);
        data.setStartPage(1);
        data.setStep(30);
        data.setUserid(bean.getUserid() + "");
        map.put(Constants.DATA, new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().getPosHistory(map), 0);
    }

    private void setAdapter(List<RetailHistoryBean> data) {
        baseAdapter = new BaseAdapter<RetailHistoryBean>(this, R.layout.item_retail_history, data, true, this) {
            @Override
            public void convert(BaseViewHolder holder, RetailHistoryBean data, int position) {
                holder.setItemViewOnClick(position);
                holder.setText(R.id.item_rh_posid, data.getPosid());
                holder.setText(R.id.item_rh_time, data.getDtime());
                holder.setText(R.id.item_rh_num, data.getTotalQty());
                holder.setText(R.id.item_rh_currency, data.getCurrency());
                holder.setText(R.id.item_rh_totalAmount, data.getTotalAmount());
                holder.setText(R.id.item_rh_received, data.getPosAmount());
            }
        };
        binding.retailHistoryRv.setRecyclerViewAdapter(baseAdapter);
        binding.retailHistoryRv.setIsData(false);
    }

    @Override
    public void updateData(List<RetailHistoryBean> data, int flags) {
        list.addAll(data);
        setAdapter(list);
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }


    @Override
    public void clickView(View v, int position) {
        this.position = position;
        Intent intent = new Intent(RetailHistoryActivity.this, RHistoryDetailActivity.class);
        intent.putExtra("posid", list.get(position).getPosid());
        LogUtils.e("posid",list.get(position).getPosid());
        intent.putExtra("data", list.get(position).getTotalAmount());
        intent.putExtra("data2", list.get(position).getPosAmount());
        intent.putExtra("state", Integer.valueOf(list.get(position).getTotalQty()) < 0);
        startActivityForResult(intent,Constants.REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SETRESULT==resultCode){
            //退单成功后重新刷新该条数据
            list.get(position).setTotalQty(-1+"");
            list.get(position).setTotalAmount("-".concat(list.get(position).getTotalAmount()));
            list.get(position).setPosAmount("-".concat(list.get(position).getPosAmount()));
            baseAdapter.notifyItemChanged(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }

}
