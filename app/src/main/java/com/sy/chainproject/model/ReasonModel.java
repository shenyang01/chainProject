package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.ReasonBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequestOther;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/7/8 10:44
 * @ author  zxcg
 */
public class ReasonModel implements BaseModelView.ModelOther<List<ReasonBean>,List<ReasonBean>>{

    @Override
    public void postData(Observable<List<ReasonBean>> Observable, BaseCallback<List<ReasonBean>> callback) {
        new HttpRequestOther<List<ReasonBean>>().doHttpDeal(Observable).subscribe(new BaseObserver<List<ReasonBean>>() {
            @Override
            public void onSucceed(List<ReasonBean> reasonBean) {
                callback.onSucceed(reasonBean);
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
