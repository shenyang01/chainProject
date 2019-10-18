package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.RetailBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.RetailDetailModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 */
public class RetailDetailPresenter extends BasePresenter<RetailBean, List<RetailBean>> {
    private BaseModelView.View<List<RetailBean>> view;
    private BaseModelView.Model<RetailBean, List<RetailBean>> model;
    public RetailDetailPresenter(BaseModelView.View<List<RetailBean>> view) {
        super(view);
        this.view = view;
        model = new RetailDetailModel();
    }

    @Override
    public void getData(Observable<BaseData<RetailBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<RetailBean>>() {
            @Override
            public void onSucceed(List<RetailBean> retailbeans) {
                view.updateData(retailbeans,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
