package com.sy.chainproject.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.ReasonBean;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityTransshipmentAddBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.ReasonPresenter;
import com.sy.chainproject.utils.SharedPreferencesUtils;
import com.sy.chainproject.utils.TypedValueUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @ data  2019/4/30 11:45
 * @ author  zxcg
 * 新增调货
 */

public class TransshipmentAddActivity extends BaseActivity implements BaseModelView.ViewOther<List<ReasonBean>> {
    private ActivityTransshipmentAddBinding binding;
    private ReasonPresenter reasonPresenter;
    private TimePickerView pvTime;
    private UserBean userBean;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_transshipment_add, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setBaseBask(getString(R.string.black));
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseTitle(getString(R.string.transshipment_add));
        binding = (ActivityTransshipmentAddBinding) bindings;
        binding.transshipmentAddDate.setText(getTime(new Date()));
        binding.transshipmentAddDate.setOnClickListener(this);
        binding.transshipmentAddBt.setOnClickListener(this);
        reasonPresenter = new ReasonPresenter(this);
        userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        getData();
    }

    /**
     * 请求参数
     */
    private void getData() {
        reasonPresenter.getDataOther(RetrofitFactory.getInstance().API().getTransferStoreType(userBean.getUserid()), 0);
        reasonPresenter.getDataOther(RetrofitFactory.getInstance().API().getTransferStoreSeason(userBean.getUserid()), 1);
        reasonPresenter.getDataOther(RetrofitFactory.getInstance().API().getTransferStoreBrand(userBean.getUserid()), 2);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.transshipment_add_date:
                getDate();
                break;
            case R.id.transshipment_add_bt:
                startActivityForResult(new Intent(TransshipmentAddActivity.this, TransshipmentDetailActivity.class), Constants.REQUESTCODE);
                break;

        }
    }


    private void setSpinnerAdapter(Spinner spinner, List<String> data) {
        if (data == null) return;
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.rv_item_sp, R.id.item_sp, data);
        spinner.setDropDownVerticalOffset(TypedValueUtils.dpTopx(this, 40));
        spinner.setAdapter(adapter);
    }

    /**
     * 时间选择器
     */
    private void getDate() {
        //时间选择器
        if (pvTime == null) {
            pvTime = new TimePickerBuilder(TransshipmentAddActivity.this, (date, v) -> {
                binding.transshipmentAddDate.setText(getTime(date));
            }).build();
            pvTime.show();
        } else pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    @Override
    public void updateOther(List<ReasonBean> data, int flags) {
        List<String> list = new ArrayList<>();
        for (ReasonBean bean : data) {
            list.add(bean.getCnName());
        }
        switch (flags) {
            case 0:
                setSpinnerAdapter(binding.transshipmentAddType, list);
                break;
            case 1:
                setSpinnerAdapter(binding.transshipmentAddSeason, list);
                break;
            case 2:
                setSpinnerAdapter(binding.transshipmentAddBrand, list);
                break;
        }
    }

    @Override
    public void onFailureOther(String e, int flags) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reasonPresenter = null;
    }
}
