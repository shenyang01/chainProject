package com.sy.chainproject.presenter;


import com.sy.chainproject.bean.BaseData;
import com.sy.chainproject.model.BaseModelView;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @ data  2018/8/14 14:41
 * @ author  zxcg
 * m v p  基类
 */
public abstract class BasePresenter<T, E> {
    public BaseModelView.View<E> view;

    BasePresenter(BaseModelView.View<E> view) {
        this.view = view;
    }


    public void detach() {  //解绑
        view = null;
    }

    public abstract void getData(Observable<BaseData<T>> Observable, int flags);

}
