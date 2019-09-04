package org.mt.androidlibrary.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.trello.rxlifecycle3.components.support.RxFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.mt.androidlibrary.other.utils.EventBusUtils
import org.mt.androidlibrary.event.DefaultEvent

/**
 *@Description:使用了DataBinding的Fragment的基类
 *@Author:zwna
 *@Date:2019-05-22
 */
abstract class DataBindingBaseFragment<BindingType:ViewDataBinding>:RxFragment() {

    lateinit var binding: BindingType
    private var mView: View? = null
    /**
     * 是否是第一次加载数据 true:是 false:不是
     */
    open var isFirstLoadData = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        EventBusUtils.regist(this)//注册EventBus
        val layoutId = getLayoutId()
        if(layoutId != 0){
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            mView = binding.root
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
        EventBusUtils.unRegist(this)//注销EventBus
        super.onDestroyView()
    }
}