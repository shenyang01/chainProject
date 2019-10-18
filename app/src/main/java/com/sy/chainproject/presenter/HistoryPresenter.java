package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.HistoryBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.HistoryModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 盘点 月报历史数据
 */
public class HistoryPresenter extends BasePresenter<HistoryBean, List<HistoryBean>> {
    private BaseModelView.View<List<HistoryBean>> view;
    private BaseModelView.Model<HistoryBean, List<HistoryBean>> model;
    public HistoryPresenter(BaseModelView.View<List<HistoryBean>> view) {
        super(view);
        this.view = view;
        model = new HistoryModel();
    }
    @Override
    public void getData(Observable<BaseData<HistoryBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<HistoryBean>>() {
            @Override
            public void onSucceed(List<HistoryBean> HistoryBean) {
                view.updateData(HistoryBean,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
