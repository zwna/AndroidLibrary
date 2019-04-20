package org.mt.androidlibrary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

/**
 *@Description:用于启动系统Activity的工具类
 *@Author:zwna
 *@Date:2019/3/28
 */
class SystemActivityUtils private constructor(){

    companion object {

        /**
         * 开启系统浏览器
         * @param context
         * @param url 要打开的网页地址
         */
        fun startSystemBrowserActivity(context: Context,url:String){
           val uri =  Uri.parse(url)
           val systemBrowser = Intent(Intent.ACTION_VIEW,uri)
           context.startActivity(systemBrowser)
        }
    }
}