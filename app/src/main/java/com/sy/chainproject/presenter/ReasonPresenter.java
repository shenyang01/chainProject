package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.ReasonBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.ReasonModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 零售历史 退货详情
 */
public class ReasonPresenter {
    private BaseModelView.ViewOther<List<ReasonBean>> view;
    private BaseModelView.ModelOther<List<ReasonBean>, List<ReasonBean>> model;

    public ReasonPresenter(BaseModelView.ViewOther<List<ReasonBean>> view) {
        this.view = view;
        model = new ReasonModel();
    }

    public void getDataOther(Observable<List<ReasonBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<ReasonBean>>() {
            @Override
            public void onSucceed(List<ReasonBean> RetailHistoryBean) {
                view.updateOther(RetailHistoryBean, flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailureOther(err, flags);
            }
        });
    }
}