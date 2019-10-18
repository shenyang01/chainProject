package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.MonthlyBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.MonthlyModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * test
 */
public class MonthlyPresenter extends BasePresenter<MonthlyBean, List<MonthlyBean>> {
    private BaseModelView.View<List<MonthlyBean>> view;
    private BaseModelView.Model<MonthlyBean, List<MonthlyBean>> model;
    public MonthlyPresenter(BaseModelView.View<List<MonthlyBean>> view) {
        super(view);
        this.view = view;
        model = new MonthlyModel();
    }
    @Override
    public void getData(Observable<BaseData<MonthlyBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<MonthlyBean>>() {
            @Override
            public void onSucceed(List<MonthlyBean> MonthlyBean) {
                view.updateData(MonthlyBean,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
