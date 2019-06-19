package org.mt.androidlibrary

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

/**
 *@Description:公共的工具类
 *@Author:zwna
 *@Date:2019/3/21
 */
class CommonUtils private constructor(){


    companion object {

        /**
         * 根据反射获取到APP的Application对象
         * @return 获取到的Application值
         */
        @SuppressLint("PrivateApi")
        fun getApplicationByReflect():Application?{
            return try {
                val activityThread = Class.forName("android.app.ActivityThread")
                val thread = activityThread.getMethod("currentActivityThread").invoke(null)
                val app = activityThread.getMethod("getApplication").invoke(thread)
                app as? Application
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        /**
         * 检测一个APP是否已经安装
         * true:已经安装 false:没有安装
         */
        fun checkAppInstalled(context: Context,pkgName:String?):Boolean{
            if (pkgName == null || "" == pkgName)
                return false
            return try {
                var info = context.packageManager.getApplicationInfo(pkgName,PackageManager.GET_UNINSTALLED_PACKAGES)
                true
            } catch (e:PackageManager.NameNotFoundException) {
                false
            }
        }


    }
}