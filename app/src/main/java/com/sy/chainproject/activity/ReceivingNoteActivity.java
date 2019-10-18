package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/5/8 15:27
 * @ author  zxcg
 * 收货单明细
 */
public class ReceivingNoteActivity extends BaseActivity implements BaseViewHolder.ViewOnclick, BaseModelView.View<List<ReceivingNoteBean>> {
    private List<ReceivingNoteBean> data;
    private ActivityReceivingNoteBinding binding;
    private ReceivingNotePresenter presenter;
    private TimePickerView pvTime;

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
        binding.receivingNoteDate.setOnClickListener(this);
        binding.receivingNote.setLayoutManager(new LinearLayoutManager(this));
        binding.receivingNoteDate.setText(getTime(new Date()));
        getReceivingNote();
    }

    private void setAdapter(List<ReceivingNoteBean> data) {
        binding.receivingNote.setAdapter(new BaseAdapter<ReceivingNoteBean>(this, R.layout.rv_item_note, data, this) {
            @Override
            public void convert(BaseViewHolder holder, ReceivingNoteBean data, int position) {
                holder.setOnclick(R.id.item_note_ll2, position);

                Glide.with(ReceivingNoteActivity.this).load(data.getImageUrl1()).into((ImageView) holder.getView(R.id.item_note_image));
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
    private void getReceivingNote() {
        presenter = new ReceivingNotePresenter(this);
        UserBean userData = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        ReceivingNoteData noteUp = new ReceivingNoteData();
        String date = binding.receivingNoteDate.getText().toString().replaceAll("-","");
       // noteUp.setD1(date.substring(0, 6) + "01");
        noteUp.setD1("20190401");
        noteUp.setD2(date);
        noteUp.setShopid(String.valueOf(userData.getShopid()));
        noteUp.setUserid(String.valueOf(userData.getUserid()));
        HashMap<String, String> map = new HashMap<>();
        map.put(Constants.DATA, new Gson().toJson(noteUp));
        LogUtils.e(new Gson().toJson(noteUp));
        presenter.getData(RetrofitFactory.getInstance().API().getReceivingNote(map), 0);
    }

    /**
     * 时间选择器
     */
    private void getDate() {
        //时间选择器
        if (pvTime == null) {
            Calendar selectedDate = Calendar.getInstance();//系统当前时间
            Calendar startDate = Calendar.getInstance();
            startDate.set(2019, 0, 1);
            Calendar endDate = Calendar.getInstance();
            endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
            pvTime = new TimePickerBuilder(ReceivingNoteActivity.this, (date, v) -> {
                binding.receivingNoteDate.setText(getTime(date));
                getReceivingNote();
            }).setType(new boolean[]{true, true, true, false, false, false}).setRangDate(startDate, endDate).setSubmitColor(getResources().getColor(R.color.colorAccent)).setDate(endDate).build();
            pvTime.show();
        } else pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    @Override
    public void clickView(View v, int position) {
        switch (v.getId()) {
            case R.id.item_note_ll2:
                Intent intent = new Intent(ReceivingNoteActivity.this, ReceivingDetailActivity.class);
                intent.putExtra("storeid", data.get(position).getOutStoreid() + "");
                startActivityForResult(intent,Constants.REQUESTCODE);
                break;
            case R.id.item_note_image:
                startActivity(new Intent(ReceivingNoteActivity.this, ImageViewPagerActivity.class));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.receiving_note_date) {
            getDate();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Constants.SETRESULT){
            getReceivingNote();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }
}
