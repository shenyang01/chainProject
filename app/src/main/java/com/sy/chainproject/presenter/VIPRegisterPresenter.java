package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.VIPRegisterModel;
import io.reactivex.Observable;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * vip 注册
 */
public class VIPRegisterPresenter extends BasePresenter<String, String> {
    private BaseModelView.View<String> view;
    private BaseModelView.Model<String, String> model;
    public VIPRegisterPresenter(BaseModelView.View<String> view) {
        super(view);
        this.view = view;
        model = new VIPRegisterModel();
    }

    @Override
    public void getData(Observable<BaseData<String>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<String>() {
            @Override
            public void onSucceed(String String) {
                view.updateData(String,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
