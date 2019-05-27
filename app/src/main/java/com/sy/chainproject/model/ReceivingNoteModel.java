package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.ReceivingNoteBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 */
public class ReceivingNoteModel implements BaseModelView.Model<ReceivingNoteBean, List<ReceivingNoteBean>> {
    @Override
    public void postData(Observable<BaseData<ReceivingNoteBean>> Observable, BaseCallback<List<ReceivingNoteBean>> callback) {
        new HttpRequest<ReceivingNoteBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<ReceivingNoteBean>>() {

            @Override
            public void onSucceed(BaseData<ReceivingNoteBean> receivingGoodsBaseData) {
                    if(receivingGoodsBaseData.status==1){
                        callback.onSucceed(receivingGoodsBaseData.data);
                    }else {
                        callback.onFailure(receivingGoodsBaseData.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
