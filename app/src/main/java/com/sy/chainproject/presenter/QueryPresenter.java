package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.QueryBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.QueryModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 */
public class QueryPresenter extends BasePresenter<QueryBean, List<QueryBean>> {
    private BaseModelView.View<List<QueryBean>> view;
    private BaseModelView.Model<QueryBean, List<QueryBean>> model;
    public QueryPresenter(BaseModelView.View<List<QueryBean>> view) {
        super(view);
        this.view = view;
        model = new QueryModel();
    }

    @Override
    public void getData(Observable<BaseData<QueryBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<QueryBean>>() {
            @Override
            public void onSucceed(List<QueryBean> querybean) {
                view.updateData(querybean,0);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,0);
            }
        });
    }
}
