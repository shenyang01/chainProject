package com.sy.chainproject.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.View;
import com.sy.chainproject.R;
import com.sy.chainproject.activity.DownActivity;
import com.sy.chainproject.activity.LoginActivity;
import com.sy.chainproject.activity.MainActivity;
import com.sy.chainproject.base.BaseActivity;
import com.sy.chainproject.base.BaseFragment;
import com.sy.chainproject.databinding.FragmentMeBinding;

import java.util.Objects;

/**
 * @ data  2019/3/20 9:25
 * @ author  zxcg
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private FragmentMeBinding binding;

    @Override
    public int getContent() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (FragmentMeBinding) bindings;
        binding.meGo.setOnClickListener(this);
        binding.meDown.setOnClickListener(this);
        binding.meEdition.setOnClickListener(this);
        binding.meExitLogin.setOnClickListener(this);
        binding.meHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_down:
                startActivity(new Intent(getActivity(), DownActivity.class));
                break;
            case R.id.me_edition:  //软件版本
                break;
            case R.id.me_exit_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.me_help:
                break;
            case R.id.me_go:
                break;
        }
    }
}
