package org.mt.androidlibrary.toast.native_toast

import android.annotation.SuppressLint
import android.widget.Toast
import org.mt.androidlibrary.CommonUtils

/**
 *@Description:原生Toast的工具类
 *@Author:zwna
 *@Date:2019/3/22
 */
@SuppressLint("ShowToast")
class NativeToastUtils private constructor(){

    companion object {

        private var toast: Toast? = null

        /**
         * 展示一个短时长的原生Toast
         * @param msg 要提示的消息
         */
        fun showShortToast(msg: String) {
            if (toast == null) {
                toast = Toast.makeText(CommonUtils.getApplicationByReflect()!!, msg, Toast.LENGTH_SHORT)
            } else {
                toast!!.setText(msg)
                toast!!.duration = Toast.LENGTH_SHORT
            }
            toast!!.show()
        }

        /**
         * 展示一个长时长的原生Toast
         * @param msg 要提示的消息
         */
        fun showLongToast(msg: String) {
            if (toast == null) {
                toast = Toast.makeText(CommonUtils.getApplicationByReflect()!!, msg, Toast.LENGTH_LONG)
            } else {
                toast!!.setText(msg)
                toast!!.duration = Toast.LENGTH_LONG
            }
            toast!!.show()
        }
    }


}