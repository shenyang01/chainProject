package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.VIPBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.VIPModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * test
 */
public class VIPPresenter extends BasePresenter<VIPBean, List<VIPBean>> {
    private BaseModelView.View<List<VIPBean>> view;
    private BaseModelView.Model<VIPBean, List<VIPBean>> model;
    public VIPPresenter(BaseModelView.View<List<VIPBean>> view) {
        super(view);
        this.view = view;
        model = new VIPModel();
    }
    @Override
    public void getData(Observable<BaseData<VIPBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<VIPBean>>() {
            @Override
            public void onSucceed(List<VIPBean> VIPBean) {
                view.updateData(VIPBean,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
