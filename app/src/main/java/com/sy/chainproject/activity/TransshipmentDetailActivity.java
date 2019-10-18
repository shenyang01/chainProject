package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.TransshipmentData;
import com.sy.chainproject.bean.TransshipmentDetailBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityTransshipmentDetailBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.TransshipmentDetailPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.view.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/7/31 9:23
 * @ author  zxcg
 * 调拨商品明细选项
 */
public class TransshipmentDetailActivity extends BaseActivity implements BaseModelView.View<List<TransshipmentDetailBean>>, BaseViewHolder.ViewOnclick {
    private BasePresenter<TransshipmentDetailBean,List<TransshipmentDetailBean>> presenter;
    private ActivityTransshipmentDetailBinding binding;
    private List list;
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_transshipment_detail,null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setBaseBask(getString(R.string.black));
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseTitle(getString(R.string.transshipment_detail));
        binding = (ActivityTransshipmentDetailBinding) bindings;
        binding.transshipmentDetailBt.setOnClickListener(this);
        getTransferStoreInfo();
        list= new ArrayList();
    }

    private void getTransferStoreInfo(){
        presenter = new TransshipmentDetailPresenter(this);
        HashMap<String,String> map = new HashMap<>();
        TransshipmentData data = new TransshipmentData();
        data.setD1("2019-4-1");
        data.setD2("2019-7-27");
        data.setUserid(1000);
        data.setSeasonName(13);
        data.setBrandName(16);
        map.put(Constants.DATA,new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().get_erp_styleInfo(map),0);
    }

    private void setAdapter(List<TransshipmentDetailBean> data){
        binding.transshipmentDetailRv.setLayoutManager(new LinearLayoutManager(this));
        binding.transshipmentDetailRv.addItemDecoration(new DividerDecoration(this));
        binding.transshipmentDetailRv.setAdapter(new BaseAdapter<TransshipmentDetailBean>(this,R.layout.rv_item_transshipment_detail,data,this){
            @Override
            public void convert(BaseViewHolder holder, TransshipmentDetailBean data, int position) {
                holder.setText(R.id.item_tDetail_styleNo,data.getStyleNo());
                //holder.setText(R.id.item_tDetail_styleName,data.getStyleName());
                holder.setText(R.id.item_tDetail_color,data.getColorName());
                holder.setText(R.id.item_tDetail_price,data.getBuyPrice()+"");
                holder.setText(R.id.item_tDetail_unit,data.getUnitName());
                holder.setText(R.id.item_tDetail_sources,data.getMadeIn());
                holder.setText(R.id.item_tDetail_date,data.getDtime().substring(0,10));
                holder.setItemViewOnClick(position);
            }
        });
    }
    @Override
    public void updateData(List<TransshipmentDetailBean> data, int flags) {
        setAdapter(data);
    }

    @Override
    public void onFailure(String e, int flags) {

    }

    @Override
    public void clickView(View v, int position) {
        BaseViewHolder holder = (BaseViewHolder) binding.transshipmentDetailRv.findViewHolderForAdapterPosition(position);
        CheckBox box =holder.getView(R.id.item_tDetail_check);
        if (box.isChecked()) {
            box.setChecked(false);
            list.remove(position+"");
        } else {
            box.setChecked(true);
            list.add(position+"");
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        for (Object i:list){
            LogUtils.e(i+"   ");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }
}
