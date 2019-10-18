package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReturnBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.ReturnGoodsModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 退货
 */
public class ReturnGoodsPresenter extends BasePresenter<ReturnBean, List<ReturnBean>> {
    private BaseModelView.View<List<ReturnBean>> view;
    private BaseModelView.Model<ReturnBean, List<ReturnBean>> model;
    public ReturnGoodsPresenter(BaseModelView.View<List<ReturnBean>> view) {
        super(view);
        this.view = view;
        model = new ReturnGoodsModel();
    }

    @Override
    public void getData(Observable<BaseData<ReturnBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<ReturnBean>>() {
            @Override
            public void onSucceed(List<ReturnBean> ReturnBeans) {
                view.updateData(ReturnBeans,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
