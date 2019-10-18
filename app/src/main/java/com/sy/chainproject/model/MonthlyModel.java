package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.MonthlyBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;


/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * test
 */
public class MonthlyModel implements BaseModelView.Model<MonthlyBean, List<MonthlyBean>> {
    @Override
    public void postData(Observable<BaseData<MonthlyBean>> Observable, BaseCallback<List<MonthlyBean>> callback) {
        new HttpRequest<MonthlyBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<MonthlyBean>>() {

            @Override
            public void onSucceed(BaseData<MonthlyBean> MonthlyBean) {
                 if(MonthlyBean.status==1){
                callback.onSucceed(MonthlyBean.data);
                }else {
                 callback.onFailure(MonthlyBean.message);
                 }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
