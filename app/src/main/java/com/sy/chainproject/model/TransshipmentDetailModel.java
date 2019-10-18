package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.TransshipmentDetailBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 调货商品详情
 */
public class TransshipmentDetailModel implements BaseModelView.Model<TransshipmentDetailBean, List<TransshipmentDetailBean>> {
    @Override
    public void postData(Observable<BaseData<TransshipmentDetailBean>> Observable, BaseCallback<List<TransshipmentDetailBean>> callback) {
        new HttpRequest<TransshipmentDetailBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<TransshipmentDetailBean>>() {

            @Override
            public void onSucceed(BaseData<TransshipmentDetailBean> data) {
                    if(data.status==1){
                        callback.onSucceed(data.data);
                    }else {
                        callback.onFailure(data.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }

}
