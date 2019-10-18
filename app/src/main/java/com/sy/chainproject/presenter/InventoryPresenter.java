package com.sy.chainproject.presenter;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.InventoryBean;
import com.sy.chainproject.model.BaseModelView;
import com.sy.chainproject.model.InventoryModel;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:25
 * @ author  zxcg
 * 盘点
 */
public class InventoryPresenter extends BasePresenter<InventoryBean, List<InventoryBean>> {
    private BaseModelView.View<List<InventoryBean>> view;
    private BaseModelView.Model<InventoryBean, List<InventoryBean>> model;
    public InventoryPresenter(BaseModelView.View<List<InventoryBean>> view) {
        super(view);
        this.view = view;
        model = new InventoryModel();
    }
    @Override
    public void getData(Observable<BaseData<InventoryBean>> Observable, int flags) {
        model.postData(Observable, new BaseCallback<List<InventoryBean>>() {
            @Override
            public void onSucceed(List<InventoryBean> InventoryBean) {
                view.updateData(InventoryBean,flags);
            }

            @Override
            public void onFailure(String err) {
                view.onFailure(err,flags);
            }
        });
    }
}
