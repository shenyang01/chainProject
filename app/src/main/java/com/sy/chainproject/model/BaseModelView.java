package com.sy.chainproject.model;


import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @ data  2018/10/9 17:25
 * @ author  zxcg
 */
public class BaseModelView {
    public interface View<E> {
        void updateData(E e, int flags);

        void onFailure(String e, int flags);
    }

    /**
     * @param <T> 返回对象类型
     * @param <E> 返回值类型,ui  回调值
     */
    public interface Model<T, E> {
        void postData(Observable<BaseData<T>> Observable, BaseCallback<E> callback);
    }
}
