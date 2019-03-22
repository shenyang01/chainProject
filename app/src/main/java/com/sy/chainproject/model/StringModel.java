package com.sy.chainproject.model;


import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Flowable;

/**
 * @ data  2018/8/24 9:20
 * @ author  zxcg
 * 无需获取返回值 通用 Model
 */
public class StringModel implements BaseModelView.Model<String, String> {
    @Override
    public void postData(Flowable<BaseData<String>> flowable, BaseCallback<String> callback) {
        new HttpRequest<String>().doHttpDeal(flowable).subscribe(new BaseObserver<BaseData>() {
            @Override
            public void onSucceed(BaseData baseData) {
                if (baseData.status.equals("1"))
                    callback.onSucceed("数据上传成功");
                else
                    callback.onFailure(baseData.message);
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
