package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequestOther;
import io.reactivex.Observable;

/**
 * @ data  2019/7/8 10:44
 * @ author  zxcg
 * 退单提交
 */
public class ReturnGoodsAddModel implements BaseModelView.ModelOther<BaseData<String>,String>{

    @Override
    public void postData(Observable<BaseData<String>> Observable, BaseCallback<String> callback) {
        new HttpRequestOther<BaseData<String>>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<String>>(){
            @Override
            public void onSucceed(BaseData<String> data) {
                if(data.status==1){
                    callback.onSucceed(data.message);
                }else
                    callback.onFailure(data.message);
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure("提交失败");
            }
        });
    }


}
