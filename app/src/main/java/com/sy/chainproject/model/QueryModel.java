package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.RetailBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 */
public class QueryModel implements BaseModelView.Model<RetailBean, List<RetailBean>> {
    @Override
    public void postData(Observable<BaseData<RetailBean>> Observable, BaseCallback<List<RetailBean>> callback) {
        new HttpRequest<RetailBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<RetailBean>>() {

            @Override
            public void onSucceed(BaseData<RetailBean> RetailBean) {
                    if(RetailBean.status==1){
                        callback.onSucceed(RetailBean.data);
                    }else {
                        callback.onFailure(RetailBean.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
