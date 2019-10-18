package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.CashBankBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.CashBankModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 盘点
 */
public class CashBankPresenter extends BasePresenter<CashBankBean, List<CashBankBean>> {
    private BaseModelView.View<List<CashBankBean>> view;
    private BaseModelView.Model<CashBankBean, List<CashBankBean>> model;
    public CashBankPresenter(BaseModelView.View<List<CashBankBean>> view) {
        super(view);
        this.view = view;
        model = new CashBankModel();
    }
    @Override
    public void getData(Observable<BaseData<CashBankBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<CashBankBean>>() {
            @Override
            public void onSucceed(List<CashBankBean> CashBankBean) {
                view.updateData(CashBankBean,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
