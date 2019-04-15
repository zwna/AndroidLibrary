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
 *@Description:Activity的工具类
 *@Author:zwna
 *@Date:2019/3/28
 */
class ActivityUtils private constructor(){

    companion object {


        /**
         * 判断一个Activity是否存在
         * @param pakName 应用包名
         * @param className 这个类的名字
         * @return true:存在 false:不存在
         */
        fun isActivityExists(pakName:String,className: String):Boolean{
            val intent = Intent()
            intent.setClassName(pakName, className)
            val applicationByReflect:Application? = CommonUtils.getApplicationByReflect()
            return if(applicationByReflect != null) {
                applicationByReflect.packageManager.resolveActivity(intent,0) == null || intent.resolveActivity(applicationByReflect.packageManager) == null || applicationByReflect.packageManager.queryIntentActivities(intent,0).size == 0
            }else{
                false
            }
        }

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

        /**
         * 启动某个Activity
         * @param context
         * @param kClass 要启动的Activity的Class对象
         */
        fun startActivity(context: Context,kClass:KClass<out Activity>){
            val intent = Intent(context,kClass.java)
            context.startActivity(intent)
        }

        /**
         * 携带bundle数据,启动某个Activity
         */
        fun startActivity(context: Context, kClass: KClass<out Activity>, bundle:Bundle){
            val intent = Intent(context,kClass.java)
            intent.putExtras(bundle)
            context.startActivity(intent,bundle)
        }



    }
}