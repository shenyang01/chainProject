package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.TransshipmentDetailBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.TransshipmentDetailModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 调货详情
 */
public class TransshipmentDetailPresenter extends BasePresenter<TransshipmentDetailBean, List<TransshipmentDetailBean>> {
    private BaseModelView.View<List<TransshipmentDetailBean>> view;
    private BaseModelView.Model<TransshipmentDetailBean, List<TransshipmentDetailBean>> model;
    public TransshipmentDetailPresenter(BaseModelView.View<List<TransshipmentDetailBean>> view) {
        super(view);
        this.view = view;
        model = new TransshipmentDetailModel();
    }

    @Override
    public void getData(Observable<BaseData<TransshipmentDetailBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<TransshipmentDetailBean>>() {
            @Override
            public void onSucceed(List<TransshipmentDetailBean> TransshipmentDetailBeans) {
                view.updateData(TransshipmentDetailBeans,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
