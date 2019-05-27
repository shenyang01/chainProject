package com.sy.chainproject.model;

import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.bean.UserBean;
import com.sy.chainproject.https.BaseObserver;
import com.sy.chainproject.https.HttpRequest;
import io.reactivex.Observable;

/**
 * @ data  2019/5/16 17:27
 * @ author  zxcg
 */
public class LoginModel implements BaseModelView.Model<UserBean, UserBean> {
    @Override
    public void postData(Observable<BaseData<UserBean>> Observable, BaseCallback<UserBean> callback) {
        new HttpRequest<UserBean>().doHttpDeal(Observable).subscribe(new BaseObserver<BaseData<UserBean>>() {

            @Override
            public void onSucceed(BaseData<UserBean> userdata) {
                    if(userdata.status==1){
                        UserBean data = new UserBean();
                        data.setUsername(userdata.username);
                        data.setAreaid(userdata.areaid);
                        data.setClientPort(userdata.clientPort);
                        data.setClietToken(userdata.clietToken);
                        data.setCsphone(userdata.csphone);
                        data.setDatabaseName(userdata.databaseName);
                        data.setIp(userdata.ip);
                        data.setUserid(userdata.userid);
                        data.setShopid(userdata.shopid);
                        data.setLogourl(userdata.logourl);
                        data.setLinkman(userdata.linkman);
                        data.setOrgName(userdata.orgName);
                        data.setPhone(userdata.phone);
                        data.setLevel(userdata.level);
                        data.setShortName(userdata.shortName);
                        data.setTelnum(userdata.telnum);
                        callback.onSucceed(data);
                    }else {
                       callback.onFailure(userdata.message);
                    }
            }

            @Override
            public void onFailure(String e) {
                callback.onFailure(e);
            }
        });
    }
}
