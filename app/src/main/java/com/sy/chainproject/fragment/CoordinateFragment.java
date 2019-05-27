package com.sy.chainproject.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.activity.*;
import com.sy.chainproject.base.BaseFragment;
import com.sy.chainproject.databinding.FragmentCoordinateBinding;

/**
 * @ data  2019/3/20 9:25
 * @ author  zxcg
 * 业务
 */
public class CoordinateFragment extends BaseFragment implements View.OnClickListener {
    private FragmentCoordinateBinding binding;
    private Intent intent;

    @Override
    public int getContent() {
        return R.layout.fragment_coordinate;
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (FragmentCoordinateBinding) bindings;
        binding.cdWeeklySales.setOnClickListener(this);
        binding.cdShopSales.setOnClickListener(this);
        binding.cdItem.setOnClickListener(this);
        binding.cdItem2.setOnClickListener(this);
        binding.cdItem3.setOnClickListener(this);
        binding.cdItem4.setOnClickListener(this);
        binding.cdItem5.setOnClickListener(this);
        binding.cdItem6.setOnClickListener(this);
        binding.cdItem7.setOnClickListener(this);
        binding.cdItem8.setOnClickListener(this);
        binding.cdItem9.setOnClickListener(this);
        binding.cdItem10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        intent = null;
        switch (v.getId()) {
            case R.id.cd_weekly_sales:
                intent = new Intent(getActivity(), WeeklySalesActivity.class);
                break;
            case R.id.cd_shop_sales:
                intent = new Intent(getActivity(), WeeklySalesActivity.class);
                break;
            case R.id.cd_item:
                break;
            case R.id.cd_item2:
                break;
            case R.id.cd_item3:
                intent = new Intent(getActivity(), ReceivingNoteActivity.class);
                break;
            case R.id.cd_item4:
                break;
            case R.id.cd_item5:
                intent = new Intent(getActivity(), CTransshipmentActivity.class);
                break;
            case R.id.cd_item6:
                intent = new Intent(getActivity(), QueryActivity.class);
                break;
            case R.id.cd_item7:
                intent = new Intent(getActivity(), BarCodeActivity.class);
                break;
            case R.id.cd_item8:
                break;
            case R.id.cd_item9:
                break;
            case R.id.cd_item10:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}