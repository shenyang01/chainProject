package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReturnGoodsDetailBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.ReturnGoodsDetailModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 退货
 */
public class ReturnGoodsDetailPresenter extends BasePresenter<ReturnGoodsDetailBean, List<ReturnGoodsDetailBean>> {
    private BaseModelView.View<List<ReturnGoodsDetailBean>> view;
    private BaseModelView.Model<ReturnGoodsDetailBean, List<ReturnGoodsDetailBean>> model;
    public ReturnGoodsDetailPresenter(BaseModelView.View<List<ReturnGoodsDetailBean>> view) {
        super(view);
        this.view = view;
        model = new ReturnGoodsDetailModel();
    }

    @Override
    public void getData(Observable<BaseData<ReturnGoodsDetailBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<ReturnGoodsDetailBean>>() {
            @Override
            public void onSucceed(List<ReturnGoodsDetailBean> ReturnGoodsDetailBeans) {
                view.updateData(ReturnGoodsDetailBeans,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
