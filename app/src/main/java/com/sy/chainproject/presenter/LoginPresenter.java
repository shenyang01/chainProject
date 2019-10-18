package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.LoginModel;
import io.reactivex.Observable;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 登录
 */
public class LoginPresenter extends BasePresenter<UserBean, UserBean> {
    private BaseModelView.View<UserBean> view;
    private BaseModelView.Model<UserBean, UserBean> model;
    public LoginPresenter(BaseModelView.View<UserBean> view) {
        super(view);
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void getData(Observable<BaseData<UserBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<UserBean>() {
            @Override
            public void onSucceed(UserBean userdata) {
                view.updateData(userdata,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
