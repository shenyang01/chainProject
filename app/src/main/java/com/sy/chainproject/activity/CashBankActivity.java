package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.CashBankBean;
import com.sy.chainproject.bean.CashBankData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityCashBankBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.CashBankPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ data  2019/7/19 9:31
 * @ author  zxcg
 * 现金银行
 */
public class CashBankActivity extends BaseActivity implements BaseModelView.View<List<CashBankBean>>, BaseViewHolder.ViewOnclick {
    private ActivityCashBankBinding binding;
    private TimePickerView pvTime;
    private BasePresenter<CashBankBean, List<CashBankBean>> presenter;
    private UserBean userBean;
    private BaseViewHolder holder;
    private List<CashBankBean> list;//  明细数据源
    private TextView view;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_cash_bank, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.account));
        binding = (ActivityCashBankBinding) bindings;
        binding.bankDate.setOnClickListener(this);
        binding.bankAdd.setOnClickListener(this);
        binding.bankDel.setOnClickListener(this);
        binding.bankDate.setText(getTime(new Date()));
        list = new ArrayList<>();
        presenter = new CashBankPresenter(this);
        userBean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        getCashBank();
    }

    /**
     * 现金获取接口
     */
    private void getCashBank() {
        HashMap map = new HashMap();
        CashBankData bean = new CashBankData();
        bean.setUserid(userBean.getUserid());
        String date = binding.bankDate.getText().toString();
        bean.setD1(date.substring(0, 8) + "01");
        bean.setD2(date);
        map.put(Constants.DATA, new Gson().toJson(bean));
        presenter.getData(RetrofitFactory.getInstance().API().getCashBank(map), 0);
    }

    private void setAdapter(List<CashBankBean> list) {
        binding.bankRv.setLayoutManager(new LinearLayoutManager(this));
        BaseAdapter<CashBankBean> adapter = new BaseAdapter<CashBankBean>(this, R.layout.rv_item_bank, list, this) {
            @Override
            public void convert(BaseViewHolder holder, CashBankBean data, int position) {
                holder.setText(R.id.bank_name, data.getCashItemName());
                holder.setEdText(R.id.bank_amount, data.getAmount() + "");
                holder.setEdText(R.id.bank_remark, data.getRemark());
                holder.setText(R.id.bank_balance, data.getBalance() + "");
                holder.setText(R.id.bank_date, data.getDtime());
                holder.setText(R.id.bank_operator, data.getUsername() + "");
                holder.setOnclick(R.id.bank_ok, position);
            }
        };
        binding.bankRv.setAdapter(adapter);
    }

    /**
     * 时间选择器
     */
    private void getDate() {
        //时间选择器
        if (pvTime == null) {
            Calendar selectedDate = Calendar.getInstance();//系统当前时间
            Calendar startDate = Calendar.getInstance();  //日期选择器起始日期
            startDate.set(2019, 0, 1);
            Calendar endDate = Calendar.getInstance();// //日期选择器结束日期
            endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DATE));
            pvTime = new TimePickerBuilder(CashBankActivity.this, (date, v) -> {
                binding.bankDate.setText(getTime(date));
                getCashBank();
            }).setType(new boolean[]{true, true, true, false, false, false}).setDate(endDate).setRangDate(startDate, endDate).
                    setSubmitColor(getResources().getColor(R.color.colorAccent)).build();
            pvTime.show();
        } else pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bank_add:
                showToast("bank_add");
                break;
            case R.id.bank_del:
                showToast("bank_del");
                break;
            case R.id.bank_date:
                getDate();
                break;
        }
    }

    @Override
    public void clickView(View v, int position) {
        holder = (BaseViewHolder) binding.bankRv.findViewHolderForAdapterPosition(position);
        view = holder.getView(R.id.bank_ok);
        if (list.get(position).getState().equals(getString(R.string.ct_update))) {
            view.setText(getString(R.string.ok));
            list.get(position).setState(getString(R.string.ok));
            holder.getView(R.id.bank_amount).setEnabled(true);
            holder.getView(R.id.bank_remark).setEnabled(true);
        } else {
            TextView textView = holder.getView(R.id.bank_amount);
            TextView textView2 = holder.getView(R.id.bank_remark);
            HashMap<String, String> map = new HashMap<>();
            CashBankData data = new CashBankData();
            data.setUserid(userBean.getUserid());
            data.setCashItem(list.get(position).getCashItem());
            data.setMkey(list.get(position).getMkey());
            data.setAmount(textView.getText().toString());
            data.setRemark(textView2.getText().toString());
            map.put(Constants.DATA, new Gson().toJson(data));
            LogUtils.e(new Gson().toJson(data));
            presenter.getData(RetrofitFactory.getInstance().API().setCashBank(map), 1);
        }
    }
    @Override
    public void updateData(List<CashBankBean> data, int flags) {
        switch (flags) {
            case 0:
                list.clear();
                list.addAll(data);
                setAdapter(list);
                break;
            case 1:  //修改成功
                view.setText(getString(R.string.ct_update));
                holder.getView(R.id.bank_amount).setEnabled(false);
                holder.getView(R.id.bank_remark).setEnabled(false);
                showToast(getString(R.string.successful_revision));
                getCashBank();
                break;
        }
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
    }
}
