package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReceivingDetailBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 */
public class ReceivingDetailModel implements BaseModelView.Model<ReceivingDetailBean, List<ReceivingDetailBean>> {
    @Override
    public void postData(Observable<BaseData<ReceivingDetailBean>> Observable, BaseCallback<List<ReceivingDetailBean>> callback) {
        new HttpRequest<ReceivingDetailBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<ReceivingDetailBean>>() {

            @Override
            public void onSucceed(BaseData<ReceivingDetailBean> ReceivingDetail) {
                    if(ReceivingDetail.status==1){
                        callback.onSucceed(ReceivingDetail.data);
                    }else {
                        callback.onFailure(ReceivingDetail.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }

    /**
     *   修改后提交
     */
    public void postData2(Observable<BaseData<ReceivingDetailBean>> Observable, BaseCallback<List<ReceivingDetailBean>> callback) {
        new HttpRequest<ReceivingDetailBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<ReceivingDetailBean>>() {

            @Override
            public void onSucceed(BaseData<ReceivingDetailBean> ReceivingDetail) {
                if(ReceivingDetail.status==1){
                    callback.onSucceed(ReceivingDetail.data);
                }else {
                    callback.onFailure(ReceivingDetail.message);
                }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
