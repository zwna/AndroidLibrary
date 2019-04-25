package org.mt.androidlibrary

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.provider.Settings.ACTION_SETTINGS
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils


/**
 *@Description:用于启动系统Activity的工具类
 *@Author:zwna
 *@Date:2019/3/28
 */
class SystemActivityUtils private constructor(){

    companion object {

        /**
         * 开启系统浏览器
         * @param url 要打开的网页地址
         */
        fun startSystemBrowserActivity(url:String){
           val uri =  Uri.parse(url)
           val systemBrowser = Intent(Intent.ACTION_VIEW,uri)
           ActivityUtils.startActivity(systemBrowser)
        }

        /**
         * 跳转到应用详情界面
         */
        fun startApplicationDetailActivity(){
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            intent.data = Uri.fromParts("package", AppUtils.getAppPackageName(), null)
            ActivityUtils.startActivity(intent)
        }

        /**
         * 跳转到设置界面
         */
        fun startSettingActivity(){
            val intent = Intent(ACTION_SETTINGS)
            ActivityUtils.startActivity(intent)
        }
    }
}