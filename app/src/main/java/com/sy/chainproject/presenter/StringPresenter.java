package com.sy.chainproject.presenter;


import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.StringModel;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @ data  2018/8/24 9:20
 * @ author  zxcg
 * 无需获取返回值 通用 Presenter
 */
public class StringPresenter extends BasePresenter<String, String> {
    private BaseModelView.View<String> view;
    private BaseModelView.Model<String, String> model;

    public StringPresenter(BaseModelView.View<String> view) {
        super(view);
        this.view = view;
        model = new StringModel();
    }

    @Override
    public void getData(Observable<BaseData<String>> Observable, int flags) {
        if (view == null) return;
        model.postData(Observable, new BaseCallback<String>() {
            @Override
            public void onSucceed(String s) {
                view.updateData(s, flags);
            }

            @Override
            public void onFailure(String s) {
                view.onFailure(s, flags);
            }
        });
    }
}


