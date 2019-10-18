package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.RetailHistoryBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.RetailHistoryModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 零售历史
 */
public class RetailHistoryPresenter extends BasePresenter<RetailHistoryBean, List<RetailHistoryBean>> {
    private BaseModelView.View<List<RetailHistoryBean>> view;
    private BaseModelView.Model<RetailHistoryBean, List<RetailHistoryBean>> model;
    public RetailHistoryPresenter(BaseModelView.View<List<RetailHistoryBean>> view) {
        super(view);
        this.view = view;
        model = new RetailHistoryModel();
    }

    @Override
    public void getData(Observable<BaseData<RetailHistoryBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<RetailHistoryBean>>() {
            @Override
            public void onSucceed(List<RetailHistoryBean> RetailHistoryBean) {
                view.updateData(RetailHistoryBean,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
