package com.sy.chainproject.fragment;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sy.chainproject.R;
import com.sy.chainproject.base.BaseFragment;
import com.sy.chainproject.databinding.FragmentProductBinding;
import com.sy.chainproject.utils.LogUtils;

/**
 * @ data  2019/3/20 9:25
 * @ author  zxcg
 * 信息
 */
public class ProductFragment extends BaseFragment {
    private FragmentProductBinding binding;
    @Override
    public int getContent() {
        return R.layout.fragment_product;
    }

    @Override
    public void initView(ViewDataBinding bindings) {
        binding = (FragmentProductBinding) bindings;
        binding.productWeb.getSettings().setJavaScriptEnabled(true);
        binding.productWeb.getSettings().setDomStorageEnabled(true);
        binding.productWeb.loadUrl("http://192.168.3.131:8080/test.html");

        //android 调用js
        binding.productWeb.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.e("onPageStarted ");
                // 开始加载页面时
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtils.e("onPageFinished ");
                // 加载结束
                binding.productWeb.evaluateJavascript("javascript:display()", value -> {
                    //此处为 js 返回的结果
                    LogUtils.e(value+"  返回");
                });
            }
        });

        //Js  调用Android 的方法
       // binding.productWeb.addJavascriptInterface(new LoginData(getActivity()),"mandroid");

        binding.productWeb.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                binding.productPro.setProgress(newProgress);
                LogUtils.e("newProgress  "+newProgress);
                if(newProgress==100)
                    binding.productPro.setVisibility(View.GONE);
                super.onProgressChanged(view, newProgress);
            }

        });
    }
}
