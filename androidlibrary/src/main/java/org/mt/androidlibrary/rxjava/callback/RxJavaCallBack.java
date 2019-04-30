package org.mt.androidlibrary.rxjava.callback;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * @Description:RxJava请求的回调基类
 * @Author:zwna
 * @Date:2019-04-30
 */
public abstract class RxJavaCallBack<T> implements Observer<T> {

    /**
     * 当收到数据的时候调用，也就是在onNext中调用
     *
     * @param t 收到的数据
     * @throws Exception 抛出的异常
     */
    public abstract void onNextResponse(@NonNull T t) throws Exception;

    /**
     * onError中调用，或者在onNext中抛出异常的时候调用
     *
     * @param throwable 产生的异常或错误对象
     */
    public void onErrorResponse(Throwable throwable) {
        LogUtils.e("retrofit exception", throwable.getMessage());
        ToastUtils.showShort(throwable.getMessage());
    }

    /**
     * 当请求到服务器数据后会有一个字段专门来标识是否是正确的数据,该方法是请求到数据,但不是正确的数据的时候调用
     */
    public abstract void onRequestError(T t);

    @Override
    public void onSubscribe(Disposable d) {}

    @Override
    public void onNext(T t) {
        try {
            onNextResponse(t);
            LogUtils.d("retrofit obj",t.toString());
        } catch (Throwable throwable) {
            onErrorResponse(throwable);
        }
    }

    @Override
    public void onError(Throwable e) {
        onErrorResponse(e);
    }

    @Override
    public void onComplete() {
        onFinishResponse();
    }

    /**
     * 当数据接受完毕的时候回调
     */
    public abstract void onFinishResponse();


}
