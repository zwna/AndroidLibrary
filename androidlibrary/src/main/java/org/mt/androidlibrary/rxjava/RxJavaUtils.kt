package org.mt.androidlibrary.rxjava

import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.android.FragmentEvent
import com.trello.rxlifecycle3.components.RxActivity
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.trello.rxlifecycle3.components.support.RxFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.mt.androidlibrary.rxjava.callback.RxJavaCallBack

/**
 *@Description:RxJava的工具类
 *@Author:zwna
 *@Date:2019-04-30
 */
class RxJavaUtils {

    companion object{
        /**
         * 在AppCompatActivity中使用RxJava进行网络请求
         */
        fun <T> useInAppCompatActivity(
            observable: Observable<T>,
            rxAppCompatActivity: RxAppCompatActivity,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxAppCompatActivity.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在Activity中使用RxJava进行网络请求
         */
        fun <T> useInActivity(
            observable: Observable<T>,
            rxActivity: RxActivity,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxActivity.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在V4包下的Fragment中使用RxJava进行网络请求
         */
        fun <T> useInV4Fragment(
            observable: Observable<T>,
            rxFragment: RxFragment,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxFragment.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在app包下的Fragment中使用RxJava进行网络请求
         */
        fun <T> useInFragment(
            observable: Observable<T>,
            rxFragment: com.trello.rxlifecycle3.components.RxFragment,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxFragment.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在AppCompatActivity中使用RxJava，当activity销毁的时候解除订阅
         */
        fun <T> useInAppCompatActivityOnDestory(
            observable: Observable<T>,
            rxAppCompatActivity: RxAppCompatActivity,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在Activity中使用RxJava，当activity销毁的时候解除订阅
         */
        fun <T> useInActivityOnDestory(
            observable: Observable<T>,
            rxActivity: RxActivity,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在V4包下的Fragment中使用RxJava进行网络请求，当Fragment销毁的时候解除订阅
         */
        fun <T> useInV4FragmentOnDestory(
            observable: Observable<T>,
            rxFragment: RxFragment,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在V4包下的Fragment中使用RxJava进行网络请求，当Fragment销毁视图的时候解除订阅
         */
        fun <T> useInV4FragmentOnDestoryView(
            observable: Observable<T>,
            rxFragment: RxFragment,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在app包下的Fragment中使用RxJava进行网络请求，当Fragment销毁的时候解除订阅
         */
        fun <T> useInFragmentOnDestory(
            observable: Observable<T>,
            rxFragment: com.trello.rxlifecycle3.components.RxFragment,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }

        /**
         * 在app包下的Fragment中使用RxJava进行网络请求，当Fragment销毁视图的时候解除订阅
         */
        fun <T> useInFragmentOnDestoryView(
            observable: Observable<T>,
            rxFragment: com.trello.rxlifecycle3.components.RxFragment,
            baseCallBack: RxJavaCallBack<T>
        ) {
            observable.compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(baseCallBack)
        }
    }


}