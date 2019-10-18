package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.CashBankBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;


/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 盘点
 */
public class CashBankModel implements BaseModelView.Model<CashBankBean, List<CashBankBean>> {
    @Override
    public void postData(Observable<BaseData<CashBankBean>> Observable, BaseCallback<List<CashBankBean>> callback) {
        new HttpRequest<CashBankBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<CashBankBean>>() {

            @Override
            public void onSucceed(BaseData<CashBankBean> CashBankBean) {
                 if(CashBankBean.status==1){
                callback.onSucceed(CashBankBean.data);
                }else {
                 callback.onFailure(CashBankBean.message);
                 }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
