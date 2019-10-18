package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.ReturnGoodsAddModel;
import io.reactivex.Observable;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 零售历史 退货详情
 */
public class ReturnGoodsAddPresenter {
    private BaseModelView.ViewOther<String> view;
    private BaseModelView.ModelOther<BaseData<String>,String> model;

    public ReturnGoodsAddPresenter(BaseModelView.ViewOther<String> view) {
        this.view = view;
        model = new ReturnGoodsAddModel();
    }

    public void getDataOther(Observable<BaseData<String>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<String>() {
            @Override
            public void onSucceed(String s) {
                view.updateOther(s, flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailureOther(err, flags);
            }
        });
    }
}