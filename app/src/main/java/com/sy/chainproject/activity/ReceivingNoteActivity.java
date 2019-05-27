package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.ReceivingNoteBean;
import com.sy.chainproject.bean.ReceivingNoteData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityReceivingNoteBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.ReceivingNotePresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/5/8 15:27
 * @ author  zxcg
 * 收货单明细
 */
public class ReceivingNoteActivity extends BaseActivity implements BaseViewHolder.ViewOnclick, BaseModelView.View<List<ReceivingNoteBean>> {
    private List<ReceivingNoteBean> data;
    private ActivityReceivingNoteBinding binding;
    private ReceivingNotePresenter presenter;
    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_receiving_note, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setBaseBask(getString(R.string.black));
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseTitle(getString(R.string.receiving_note));
        binding = (ActivityReceivingNoteBinding) bindings;
        binding.receivingNote.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }

    private void setAdapter(List<ReceivingNoteBean> data) {
        binding.receivingNote.setAdapter(new BaseAdapter<ReceivingNoteBean>(this, R.layout.rv_item_note, data, this) {
            @Override
            public void convert(BaseViewHolder holder, ReceivingNoteBean data, int position) {
                holder.setOnclick(R.id.item_note_ll2, position);

                Glide.with(ReceivingNoteActivity.this).load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1558339950&di=ec7ea1a3602fda91e07910d1ba3a5682&src=http://pic36.nipic.com/20131110/14741728_170149392100_2.jpg").into((ImageView) holder.getView(R.id.item_note_image));
                holder.setOnclick(R.id.item_note_image, position);
                holder.setText(R.id.item_note_name, data.getStoreNum());
                holder.setText(R.id.item_note_time, data.getDtime());
                holder.setText(R.id.item_note_style, data.getTypeName() + "\t" + data.getStyleNo());
                holder.setText(R.id.item_note_info, data.getStyleName());
                holder.setText(R.id.item_note_number, getString(R.string.ct_num).concat(String.valueOf(data.getTotalQty())));
                holder.setText(R.id.item_note_pAmount, getString(R.string.pAmount) + data.getPAmount());
                holder.setText(R.id.item_note_discount, getString(R.string.discount) + data.getDiscount());
                holder.setText(R.id.item_note_actAmount, getString(R.string.actAmount) + getString(R.string.money).concat(String.valueOf(data.getActAmount())));
            }
        });
    }

    /**
     * 网络请求
     */
    private void getData() {
        presenter = new ReceivingNotePresenter(this);
        UserBean userData = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        ReceivingNoteData noteUp = new ReceivingNoteData();
        noteUp.setD1("20190101");
        noteUp.setD2("20190601");
        noteUp.setShopid(String.valueOf(userData.getShopid()));
        noteUp.setUserid(String.valueOf(userData.getUserid()));
        HashMap<String, String> map = new HashMap<>();
        map.put(Constants.DATA, new Gson().toJson(noteUp));
        LogUtils.e(new Gson().toJson(noteUp));
        presenter.getData(RetrofitFactory.getInstance().API().getReceivingNote(map), 0);
    }

    @Override
    public void clickView(View v, int position) {
        switch (v.getId()) {
            case R.id.item_note_ll2:
               Intent intent =new Intent(ReceivingNoteActivity.this, ReceivingDetailActivity.class);
                intent.putExtra("storeid",data.get(position).getStoreid()+"");
                startActivity(intent);
                break;
            case R.id.item_note_image:
                startActivity(new Intent(ReceivingNoteActivity.this, ImageViewPagerActivity.class));
                break;
        }
    }

    @Override
    public void updateData(List<ReceivingNoteBean> data, int flags) {
        this.data = data;
        setAdapter(data);
    }

    @Override
    public void onFailure(String e, int flags) {

    }
}
