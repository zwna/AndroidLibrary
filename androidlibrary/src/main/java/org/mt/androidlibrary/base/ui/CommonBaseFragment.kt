package org.mt.androidlibrary.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle3.components.support.RxFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.mt.androidlibrary.EventBusUtil
import org.mt.androidlibrary.event.DefaultEvent

/**
 *@Description:没有使用databinding的Fragment基类
 *@Author:zwna
 *@Date:2019-06-19
 */
abstract class CommonBaseFragment:RxFragment() {

    private var mView: View? = null
    /**
     * 是否是第一次加载数据 true:是 false:不是
     */
    open var isFirstLoadData = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        EventBusUtil.regist(this)//注册EventBus
        val layoutId = getLayoutId()
        if(layoutId != 0){
            mView = inflater.inflate(getLayoutId(), container, false)
        }
        initView()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    /**
     * 当该Fragment对用户可见时或不可见时调用
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(activity != null && userVisibleHint){
            lazyLoadData()
        }
    }

    /**
     * 获取布局ID
     */
    abstract fun getLayoutId():Int

    /**
     * 懒加载数据
     */
    open fun lazyLoadData(){

    }

    /**
     * 初始化控件
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 为控件设置单击事件
     */
    fun setViewsOnClickListener(listener: View.OnClickListener, vararg view: View){
        view.forEach {
            it.setOnClickListener(listener)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun defaultEvent(event: DefaultEvent) {
    }

    override fun onDestroyView() {
        EventBusUtil.unRegist(this)//注销EventBus
        super.onDestroyView()
    }
}