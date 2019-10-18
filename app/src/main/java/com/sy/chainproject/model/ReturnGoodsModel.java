package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReturnBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 退货单请求
 */
public class ReturnGoodsModel implements BaseModelView.Model<ReturnBean, List<ReturnBean>> {
    @Override
    public void postData(Observable<BaseData<ReturnBean>> Observable, BaseCallback<List<ReturnBean>> callback) {
        new HttpRequest<ReturnBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<ReturnBean>>() {

            @Override
            public void onSucceed(BaseData<ReturnBean> data) {
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
