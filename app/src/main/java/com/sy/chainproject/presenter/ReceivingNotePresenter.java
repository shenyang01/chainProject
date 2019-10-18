package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReceivingNoteBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.ReceivingNoteModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 */
public class ReceivingNotePresenter extends BasePresenter<ReceivingNoteBean, List<ReceivingNoteBean>> {
    private BaseModelView.View<List<ReceivingNoteBean>> view;
    private BaseModelView.Model<ReceivingNoteBean, List<ReceivingNoteBean>> model;
    public ReceivingNotePresenter(BaseModelView.View<List<ReceivingNoteBean>> view) {
        super(view);
        this.view = view;
        model = new ReceivingNoteModel();
    }

    @Override
    public void getData(Observable<BaseData<ReceivingNoteBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<ReceivingNoteBean>>() {
            @Override
            public void onSucceed(List<ReceivingNoteBean> receivingGoods) {
                view.updateData(receivingGoods,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
