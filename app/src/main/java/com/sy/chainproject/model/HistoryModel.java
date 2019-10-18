package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.HistoryBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;


/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 盘点 月报历史数据
 */
public class HistoryModel implements BaseModelView.Model<HistoryBean, List<HistoryBean>> {
    @Override
    public void postData(Observable<BaseData<HistoryBean>> Observable, BaseCallback<List<HistoryBean>> callback) {
        new HttpRequest<HistoryBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<HistoryBean>>() {

            @Override
            public void onSucceed(BaseData<HistoryBean> HistoryBean) {
                 if(HistoryBean.status==1){
                callback.onSucceed(HistoryBean.data);
                }else {
                 callback.onFailure(HistoryBean.message);
                 }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
