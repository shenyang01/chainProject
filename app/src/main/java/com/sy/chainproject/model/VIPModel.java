package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.VIPBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;


/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * VIP 用戶信息
 */
public class VIPModel implements BaseModelView.Model<VIPBean, List<VIPBean>> {
    @Override
    public void postData(Observable<BaseData<VIPBean>> Observable, BaseCallback<List<VIPBean>> callback) {
        new HttpRequest<VIPBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<VIPBean>>() {

            @Override
            public void onSucceed(BaseData<VIPBean> VIPBean) {
                 if(VIPBean.status==1){
                callback.onSucceed(VIPBean.data);
                }else {
                 callback.onFailure(VIPBean.message);
                 }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
