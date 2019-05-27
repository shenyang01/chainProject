package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReceivingDetailBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.ReceivingDetailModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 */
public class ReceivingDetailPresenter extends BasePresenter<ReceivingDetailBean, List<ReceivingDetailBean>> {
    private BaseModelView.View<List<ReceivingDetailBean>> view;
    private ReceivingDetailModel model;
    public ReceivingDetailPresenter(BaseModelView.View<List<ReceivingDetailBean>> view) {
        super(view);
        this.view = view;
        model = new ReceivingDetailModel();
    }

    @Override
    public void getData(Observable<BaseData<ReceivingDetailBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<ReceivingDetailBean>>() {
            @Override
            public void onSucceed(List<ReceivingDetailBean> receivingdetail) {
                view.updateData(receivingdetail,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }

    /**
     *
     * @param Observable  修改后提交
     */
    public void getData2(Observable<BaseData<ReceivingDetailBean>> Observable, int flags) {
        model.postData2(Observable, new BaseCallback<List<ReceivingDetailBean>>() {
            @Override
            public void onSucceed(List<ReceivingDetailBean> string) {
                view.updateData(string,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
