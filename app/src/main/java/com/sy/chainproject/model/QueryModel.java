package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.QueryBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 */
public class QueryModel implements BaseModelView.Model<QueryBean, List<QueryBean>> {
    @Override
    public void postData(Observable<BaseData<QueryBean>> Observable, BaseCallback<List<QueryBean>> callback) {
        new HttpRequest<QueryBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<QueryBean>>() {

            @Override
            public void onSucceed(BaseData<QueryBean> querybean) {
                    if(querybean.status==1){
                        callback.onSucceed(querybean.data);
                    }else {
                        callback.onFailure(querybean.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
