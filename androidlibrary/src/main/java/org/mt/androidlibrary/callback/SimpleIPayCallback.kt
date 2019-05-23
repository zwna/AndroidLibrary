package org.mt.androidlibrary.callback

import com.xgr.easypay.callback.IPayCallback

/**
 *@Description:
 *@Author:zwna
 *@Date:2019-05-23
 */
class SimpleIPayCallback:IPayCallback {

    /**
     * 支付失败回调
     */
    override fun failed() {

    }

    /**
     * 取消支付回调
     */
    override fun cancel() {
    }

    /**
     * 支付成功回调
     */
    override fun success() {
    }
}