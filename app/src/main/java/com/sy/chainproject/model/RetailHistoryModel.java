package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.RetailHistoryBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 零售历史
 */
public class RetailHistoryModel implements BaseModelView.Model<RetailHistoryBean, List<RetailHistoryBean>> {
    @Override
    public void postData(Observable<BaseData<RetailHistoryBean>> Observable, BaseCallback<List<RetailHistoryBean>> callback) {
        new HttpRequest<RetailHistoryBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<RetailHistoryBean>>() {

            @Override
            public void onSucceed(BaseData<RetailHistoryBean> RetailHistoryBean) {
                    if(RetailHistoryBean.status==1){
                        callback.onSucceed(RetailHistoryBean.data);
                    }else {
                        callback.onFailure(RetailHistoryBean.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }

}
