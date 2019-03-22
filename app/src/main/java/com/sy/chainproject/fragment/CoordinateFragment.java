package com.sy.chainproject.fragment;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.sy.chainproject.R;
import com.sy.chainproject.adapter.BaseAdapter;
import com.sy.chainproject.adapter.BaseViewHolder;
import com.sy.chainproject.base.BaseFragment;
import com.sy.chainproject.databinding.FragmentCoordinateBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ data  2019/3/20 9:25
 * @ author  zxcg
 */
public class CoordinateFragment extends BaseFragment implements View.OnClickListener, BaseViewHolder.ViewOnclick {
    private FragmentCoordinateBinding binding;

    @Override
    public int getContent() {
        return R.layout.fragment_coordinate;
    }

    @Override
    public void initView(ViewDataBinding bindings) {

        List<String> list = new ArrayList<>();
        list.add(getString(R.string.retail));
        list.add(getString(R.string.wholesale));
        list.add(getString(R.string.receiving));
        list.add(getString(R.string.return_goods));
        list.add(getString(R.string.transfer));
        list.add(getString(R.string.query));
        list.add(getString(R.string.bar_code));
        list.add(getString(R.string.account));
        list.add(getString(R.string.vip));
        list.add(getString(R.string.report_form));

        binding = (FragmentCoordinateBinding) bindings;
        binding.cdWeeklySales.setOnClickListener(this);
        binding.cdShopSales.setOnClickListener(this);
        binding.coordinateRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.coordinateRv.setAdapter(new BaseAdapter(getContext(), R.layout.coordinate_rv_item, list, false, this) {
            @Override
            public void convert(BaseViewHolder holder, String data, int position) {
                holder.setText(R.id.cd_item_tv, data);
                holder.setOnclick(R.id.cd_itemView, position);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void clickView(View v, int position) {
        Toast.makeText(getActivity(), position + " ", Toast.LENGTH_SHORT).show();
    }
}
