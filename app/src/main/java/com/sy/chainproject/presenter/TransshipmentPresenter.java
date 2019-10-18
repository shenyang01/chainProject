package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.TransshipmentBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.TransshipmentModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 调货单
 */
public class TransshipmentPresenter extends BasePresenter<TransshipmentBean, List<TransshipmentBean>> {
    private BaseModelView.View<List<TransshipmentBean>> view;
    private BaseModelView.Model<TransshipmentBean, List<TransshipmentBean>> model;
    public TransshipmentPresenter(BaseModelView.View<List<TransshipmentBean>> view) {
        super(view);
        this.view = view;
        model = new TransshipmentModel();
    }

    @Override
    public void getData(Observable<BaseData<TransshipmentBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<TransshipmentBean>>() {
            @Override
            public void onSucceed(List<TransshipmentBean> TransshipmentBeans) {
                view.updateData(TransshipmentBeans,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
