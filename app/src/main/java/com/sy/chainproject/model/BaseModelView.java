package com.sy.chainproject.model;


import com.sy.chainproject.base.BaseCallback;
import com.sy.chainproject.bean.BaseData;
import io.reactivex.Observable;

/**
 * @ data  2018/10/9 17:25
 * @ author  zxcg
 */
public class BaseModelView {
    public interface View<E> {
        void updateData(E data, int flags);

        void onFailure(String e, int flags);
    }
    public interface ViewOther<E> {
        void updateOther(E data, int flags);

        void onFailureOther(String e, int flags);
    }
    /**
     * @param <T> 返回对象类型
     * @param <E> 返回值类型,ui  回调值
     */
    public interface Model<T, E> {
        void postData(Observable<BaseData<T>> Observable, BaseCallback<E> callback);
    }
    public interface ModelOther<T, E> {
        void postData(Observable<T> Observable, BaseCallback<E> callback);
    }
}
