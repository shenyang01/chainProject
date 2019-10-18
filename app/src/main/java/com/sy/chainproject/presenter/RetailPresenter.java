package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.RetailBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.RetailModel;
import io.reactivex.Observable;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 零售
 */
public class RetailPresenter extends BasePresenter<RetailBean, RetailBean> {
    private BaseModelView.View<RetailBean> view;
    private RetailModel model;
    public RetailPresenter(BaseModelView.View<RetailBean> view) {
        super(view);
        this.view = view;
        model = new RetailModel();
    }

    @Override
    public void getData(Observable<BaseData<RetailBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<RetailBean>() {
            @Override
            public void onSucceed(RetailBean retailbean) {
                view.updateData(retailbean,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }

}
