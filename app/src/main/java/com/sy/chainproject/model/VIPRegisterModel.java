package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * VIP 注册
 */
public class VIPRegisterModel implements BaseModelView.Model<String, String> {
    @Override
    public void postData(Observable<BaseData<String>> Observable, BaseCallback<String> callback) {
        new HttpRequest<String>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<String>>() {

            @Override
            public void onSucceed(BaseData<String> data) {
                    if(data.status==1){
                        callback.onSucceed(data.message);
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
