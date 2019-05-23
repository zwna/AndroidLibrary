package org.mt.androidlibrary.base.view

import com.blankj.utilcode.util.ToastUtils

/**
 *@Description:
 *@Author:zwna
 *@Date:2019-05-22
 */
interface BaseView {

    /**
     * 展示短时间的原生Toast
     */
    fun showNativeShortToast(message:String){
        ToastUtils.showShort(message)
    }

    /**
     * 展示长时间的原生Toast
     */
    fun showNativeLongToast(message:String){
        ToastUtils.showLong(message)
    }


}