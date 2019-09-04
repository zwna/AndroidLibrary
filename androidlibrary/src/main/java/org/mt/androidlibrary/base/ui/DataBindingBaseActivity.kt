package org.mt.androidlibrary.base.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.LogUtils
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.mt.androidlibrary.other.utils.EventBusUtils
import org.mt.androidlibrary.event.DefaultEvent
import org.mt.androidlibrary.slide_back.SwipeBackActivityHelper
import org.mt.androidlibrary.slide_back.Util.convertActivityFromTranslucent
import org.mt.androidlibrary.slide_back.SwipeBackLayout







/**
 *@Description:使用了DataBinding的Activity的基类
 *@Author:zwna
 *@Date:2019-04-30
 */
abstract class DataBindingBaseActivity<BindingType:ViewDataBinding> : RxAppCompatActivity() {

    lateinit var context:Context

    lateinit var binding: BindingType

    private lateinit var mHelper: SwipeBackActivityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingBeforeSetContentView(savedInstanceState)
        val layoutId = getLayoutId()
        if(layoutId != 0){
            binding = DataBindingUtil.setContentView(this, layoutId)
        }

        context = this
        mHelper =  SwipeBackActivityHelper(this)
        //是否支持缩放动画
        mHelper.onActivityCreate(isSupportFinishAnim())
        //是否支持滑动返回
        setSwipeBackEnable(isSupportSwipeBack())

        initView(savedInstanceState)
        EventBusUtils.regist(this)
        initData(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        LogUtils.e("zh","onPostCreate  mActivity ========" + DataBindingBaseActivity::class.java.simpleName)
        mHelper.onPostCreate();
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        if (getSwipeBackLayout().finishAnim && !getSwipeBackLayout().mIsActivitySwipeing) {
            convertActivityFromTranslucent(this)
            getSwipeBackLayout().mIsActivityTranslucent = false
            LogUtils.e("zh", "onEnterAnimationComplete  mActivity ========" + DataBindingBaseActivity::class.java.simpleName)
        }
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     * @return
     */
    open fun isSupportSwipeBack(): Boolean {
        return true
    }

    open fun isSupportFinishAnim(): Boolean {
        return true
    }

    open fun getSwipeBackLayout(): SwipeBackLayout {
        return mHelper.swipeBackLayout
    }

    open fun setSwipeBackEnable(enable: Boolean) {
        getSwipeBackLayout().setEnableGesture(enable)
    }

    fun getSwipeBackEnable(): Boolean {
        return getSwipeBackLayout().swipeBackEnable
    }

    override fun finish() {
        super.finish()
        mHelper.finish()
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
        EventBusUtils.unRegist(this)
        super.onDestroy()
    }

}
