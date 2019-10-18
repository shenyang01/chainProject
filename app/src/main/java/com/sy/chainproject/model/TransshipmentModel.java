package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.TransshipmentBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 调货
 */
public class TransshipmentModel implements BaseModelView.Model<TransshipmentBean, List<TransshipmentBean>> {
    @Override
    public void postData(Observable<BaseData<TransshipmentBean>> Observable, BaseCallback<List<TransshipmentBean>> callback) {
        new HttpRequest<TransshipmentBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<TransshipmentBean>>() {

            @Override
            public void onSucceed(BaseData<TransshipmentBean> data) {
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
