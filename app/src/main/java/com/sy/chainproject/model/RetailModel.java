package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.RetailBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;


/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 零售
 */
public class RetailModel implements BaseModelView.Model<RetailBean, RetailBean> {
    @Override
    public void postData(Observable<BaseData<RetailBean>> Observable, BaseCallback<RetailBean> callback) {
        new HttpRequest<RetailBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<RetailBean>>() {

            @Override
            public void onSucceed(BaseData<RetailBean> retailbean) {
                    if(retailbean.status==1){
                        if(retailbean.data==null){
                            callback.onSucceed(new RetailBean());
                        }
                        callback.onSucceed(retailbean.data.get(0));
                    }else {
                        callback.onFailure(retailbean.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }

}
