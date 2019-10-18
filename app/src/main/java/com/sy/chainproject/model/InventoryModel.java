package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.InventoryBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

import java.util.List;


/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 * 盘点
 */
public class InventoryModel implements BaseModelView.Model<InventoryBean, List<InventoryBean>> {
    @Override
    public void postData(Observable<BaseData<InventoryBean>> Observable, BaseCallback<List<InventoryBean>> callback) {
        new HttpRequest<InventoryBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<InventoryBean>>() {

            @Override
            public void onSucceed(BaseData<InventoryBean> inventorybean) {
                 if(inventorybean.status==1){
                callback.onSucceed(inventorybean.data);
                }else {
                 callback.onFailure(inventorybean.message);
                 }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
