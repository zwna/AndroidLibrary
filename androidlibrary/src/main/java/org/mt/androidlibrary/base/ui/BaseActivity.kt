package org.mt.androidlibrary.base.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.mt.androidlibrary.EventBusUtil
import org.mt.androidlibrary.event.DefaultEvent

/**
 *@Description:Activity的基类
 *@Author:zwna
 *@Date:2019-04-30
 */
abstract class BaseActivity<BindingType:ViewDataBinding> : RxAppCompatActivity() {

    lateinit var context:Context

    lateinit var binding: BindingType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingBeforeSetContentView(savedInstanceState)
        val layoutId = getLayoutId()
        if(layoutId != 0){
            binding = DataBindingUtil.setContentView(this, layoutId)
        }

        context = this
        initView(savedInstanceState)
        EventBusUtil.regist(this)
        initData(savedInstanceState)
    }

    /**
     * 为控件设置单击事件
     */
    fun setViewsOnClickListener(listener: View.OnClickListener,vararg view:View){
      view.forEach {
          it.setOnClickListener(listener)
      }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun defaultEvent(event: DefaultEvent) {
    }



    /**
     * 用于获取布局的ID
     */
    abstract fun getLayoutId():Int

    /**
     * 在setContentView方法调用之前调用的方法
     */
    open fun settingBeforeSetContentView(savedInstanceState: Bundle?){

    }

    /**
     * 初始化控件
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 设置数据的方法
     */
    abstract fun initData(savedInstanceState: Bundle?)


    override fun onDestroy() {
        EventBusUtil.unRegist(this)
        super.onDestroy()
    }

}
