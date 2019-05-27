package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.databinding.ActivityTransshipmentBinding;
import com.sy.chainproject.utils.TypedValueUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @ data  2019/4/30 11:45
 * @ author  zxcg
 * 调货
 */

public class CTransshipmentActivity extends BaseActivity {
    private ActivityTransshipmentBinding binding;
    private int index;
    private List<View> list;
    private List<String> data;
    private TimePickerView pvTime;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_transshipment, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setBaseBask(getString(R.string.black));
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseTitle(getString(R.string.transfer));
        binding = (ActivityTransshipmentBinding) bindings;
        list = new ArrayList<>();
        binding.ctAddGoods.setOnClickListener(this);
        binding.ctSubmission.setOnClickListener(this);
        binding.ctTime.setOnClickListener(this);
        data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        setSpinnerAdapter(binding.ctBranchName, data);
        setSpinnerAdapter(binding.ctBranchName, data);

        addGoods();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ct_add_goods:
                addGoods();
                break;
            case R.id.ct_goods_delete:
                goodsDelete(v);
                break;
            case R.id.ct_submission:
                goodsInfo();
                break;
            case R.id.ct_time:
                getDate();
                break;
        }
    }

    private void goodsDelete(View v) {
        if (list.size() == 1) return;
        index--;
        int tag = Integer.parseInt(v.getTag().toString());
        binding.ctGoodsLl.removeView(list.get(tag));
        list.remove(tag);
        //重新设置Tag
        for (int i = 0; i < list.size(); i++) {
            list.get(i).findViewById(R.id.ct_goods_delete).setTag(i);
        }
    }

    private void addGoods() {
        index++;
        View view = LayoutInflater.from(this).inflate(R.layout.add_merchandise, null);
        TextView goods_in = view.findViewById(R.id.ct_goods_in);
        goods_in.setText(getString(R.string.ct_goods).concat(index + ""));
        TextView del = view.findViewById(R.id.ct_goods_delete);
        del.setOnClickListener(this);
        del.setTag(list.size());
        setSpinnerAdapter(view.findViewById(R.id.ct_goods_sp), data);
        setSpinnerAdapter(view.findViewById(R.id.ct_goods_sp2), data);
        setSpinnerAdapter(view.findViewById(R.id.ct_goods_sp3), data);
        setSpinnerAdapter(view.findViewById(R.id.ct_goods_sp4), data);
        binding.ctGoodsLl.addView(view);
        list.add(view);
    }

    /**
     * 获取全部商品信息
     */
    private void goodsInfo() {
        for (int i = 0; i < list.size(); i++) {
            TextView ct_goods_num = list.get(i).findViewById(R.id.ct_goods_num);
            Log.e("tag", "  " + ct_goods_num.getText());
        }
    }

    private void setSpinnerAdapter(Spinner spinner, List<String> data) {
        if (data == null) return;
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.rv_item_sp, R.id.item_sp, data);
        spinner.setDropDownVerticalOffset(TypedValueUtils.dpTopx(this, 50));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView view1 = (TextView) view;
                view1.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setAdapter(adapter);
    }

    /**
     * 时间选择器
     */
    private void getDate() {
        //时间选择器
        if (pvTime == null) pvTime = new TimePickerBuilder(CTransshipmentActivity.this, (date, v) -> {

            binding.ctTime.setText(getTime(date));
        }).build();
        else pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    @Override
    protected void onDestroy() {
        list.clear();
        list = null;
        super.onDestroy();
    }
}
