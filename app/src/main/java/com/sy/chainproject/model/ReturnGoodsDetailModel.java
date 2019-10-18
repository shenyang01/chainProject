package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReturnGoodsDetailBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 退货单请求
 */
public class ReturnGoodsDetailModel implements BaseModelView.Model<ReturnGoodsDetailBean, List<ReturnGoodsDetailBean>> {
    @Override
    public void postData(Observable<BaseData<ReturnGoodsDetailBean>> Observable, BaseCallback<List<ReturnGoodsDetailBean>> callback) {
        new HttpRequest<ReturnGoodsDetailBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<ReturnGoodsDetailBean>>() {

            @Override
            public void onSucceed(BaseData<ReturnGoodsDetailBean> data) {
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
