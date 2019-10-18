package com.sy.chainproject.activity;

import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import com.google.gson.Gson;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.bean.ReasonBean;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.bean.VIPRegisterData;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.databinding.ActivityVipRegisterBinding;
import com.sy.chainproject.https.RetrofitFactory;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.presenter.BasePresenter;
import com.sy.chainproject.presenter.ReasonPresenter;
import com.sy.chainproject.presenter.VIPRegisterPresenter;
import com.sy.chainproject.utils.LogUtils;
import com.sy.chainproject.utils.SharedPreferencesUtils;
import com.sy.chainproject.utils.TypedValueUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ data  2019/7/24 11:00
 * @ author  zxcg
 * VIP 新增页面
 */
public class VIPRegisterActivity extends BaseActivity implements BaseModelView.View<String>, BaseModelView.ViewOther<List<ReasonBean>> {
    private ActivityVipRegisterBinding binding;
    private UserBean bean;
    private BasePresenter<String, String> presenter;
    private ReasonPresenter otherPresenter;

    @Override
    public View getContent() {
        return LayoutInflater.from(this).inflate(R.layout.activity_vip_register, null);
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        setColor(getResources().getColor(R.color.bg_title_bar));
        setBaseBask(getString(R.string.black));
        setBaseTitle(getString(R.string.vip_register));
        binding = (ActivityVipRegisterBinding) bindings;
        binding.vipRegister.setOnClickListener(this);
        bean = SharedPreferencesUtils.getUserdata(Constants.USERDATA);
        getVipCardType();
    }


    private void setSpinnerAdapter(List<String> data) {
        if (data == null) return;
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.rv_item_sp, R.id.item_sp, data);
        binding.vipRegisterCard.setDropDownVerticalOffset(TypedValueUtils.dpTopx(this, 40));
        binding.vipRegisterCard.setAdapter(adapter);
    }

    /**
     * 获取卡别
     */
    private void getVipCardType() {
        otherPresenter = new ReasonPresenter(this);
        otherPresenter.getDataOther(RetrofitFactory.getInstance().API().getVipCardType(bean.getUserid()), 0);
    }

    /**
     * 新增用户
     */
    private void addVipInfo() {
        String phone = binding.vipRegisterPhone.getText().toString();
        String name = binding.vipRegisterName.getText().toString();
        int card = binding.vipRegisterCard.getSelectedItemPosition();
        String money = binding.vipRegisterMoney.getText().toString();
        String remark = binding.vipRegisterRemark.getText().toString();
        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(name)||TextUtils.isEmpty(money)){
            showToast(getString(R.string.vip_empty));
            return;
        }
        presenter = new VIPRegisterPresenter(this);
        HashMap<String,String> map = new HashMap<>();
        VIPRegisterData data = new VIPRegisterData();
        data.setPhone(phone);
        data.setAmount(money);
        data.setLevel(card);
        data.setMkey(-1);
        data.setCustomerName(name);
        data.setUserid(bean.getUserid());
        data.setRemark(remark);
        map.put(Constants.DATA,new Gson().toJson(data));
        LogUtils.e(new Gson().toJson(data));
        presenter.getData(RetrofitFactory.getInstance().API().addVipInfo(map),1);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId()==R.id.vip_register)
            addVipInfo();
    }

    @Override
    public void updateData(String data, int flags) {
        showToast(data);
        setResult(Constants.SETRESULT);
        finish();
    }

    @Override
    public void onFailure(String e, int flags) {
        showToast(e);
    }

    @Override
    public void updateOther(List<ReasonBean> data, int flags) {
        List<String> list = new ArrayList<>();
        for (ReasonBean bean : data) {
            list.add(bean.getCnName());
        }
        setSpinnerAdapter(list);
    }

    @Override
    public void onFailureOther(String e, int flags) {
        showToast(e);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter(presenter);
        if (otherPresenter != null) otherPresenter = null;
    }
}
