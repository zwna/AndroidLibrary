package org.mt.androidlibrary.other.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings.ACTION_SETTINGS
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import org.mt.androidlibrary.CommonUtils


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

        /**
         * 跳转到直接拨打电话的界面
         * @param phoneNumber 要拨打的电话号码
         */
        fun startCallActivity(phoneNumber:String){
            val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))//直接拨打电话
            ActivityUtils.startActivity(dialIntent)
        }

        /**
         * 跳转到拨号界面
         */
        fun startDialActivity(){
            val dialIntent = Intent(Intent.ACTION_CALL_BUTTON)//跳转到拨号界面
            ActivityUtils.startActivity(dialIntent)
        }

        /**
         * 跳转到拨号界面并携带电话号码
         * @param phoneNumber 要携带的电话号码
         */
        fun startDialActivityWithPhoneNumber(phoneNumber: String){
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))//跳转到拨号界面，同时传递电话号码
            ActivityUtils.startActivity(dialIntent)
        }

        /**
         * 跳转到发短信界面
         * @param smsContent 要发送的短信
         */
        fun startSmsActivity(smsContent:String){
            val it = Intent(Intent.ACTION_VIEW)
            it.putExtra("sms_body", smsContent)
            it.type = "vnd.android-dir/mms-sms"
            ActivityUtils.startActivity(it)
        }

        /**
         * 跳转到发短信界面
         * @param phoneNumber 接收短信的手机号
         * @param smsContent 短信内容
         */
        fun startSmsActivity(phoneNumber:String,smsContent:String){
            val uri = Uri.parse("smsto:$phoneNumber")
            val it = Intent(Intent.ACTION_SENDTO, uri)
            it.putExtra("sms_body", smsContent)
            ActivityUtils.startActivity(it)
        }

        /**
         * 跳转到发送email界面
         * @param emailAddress 接收者的邮箱地址
         */
        fun startSendEmailActivity(emailAddress:String){
            val uri = Uri.parse("mailto:$emailAddress")
            val it = Intent(Intent.ACTION_SENDTO, uri)
            ActivityUtils.startActivity(it)
        }

        /**
         * 打开QQ个人界面
         * @param qqNumber 目标QQ号码
         */
        fun openQQ(context: Context,qqNumber:String){
          if(CommonUtils.checkAppInstalled(context, "com.tencent.mobileqq")){
              val qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=$qqNumber&version=1"
              val intent = Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl))
              ActivityUtils.startActivity(intent)
          }else{
              ToastUtils.showShort("本机未安装QQ")
          }
        }

        /**
         * 打开QQ群聊界面
         * @param qqNumber 目标QQ号码
         */
        fun openQQGroup(context: Context,qqNumber:String){
            if(CommonUtils.checkAppInstalled(context, "com.tencent.mobileqq")){
                val qqUrl = "mqqwpa://im/chat?chat_type=group&uin=$qqNumber&version=1"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl))
                ActivityUtils.startActivity(intent)
            }else{
                ToastUtils.showShort("本机未安装QQ")
            }
        }

        /**
         * 打开微信
         */
        fun openWX(context: Context){
            if(CommonUtils.checkAppInstalled(context, "com.tencent.mm")){
                val intent = Intent()
                val cmp = ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI")
                intent.action = Intent.ACTION_MAIN
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.component = cmp
                ActivityUtils.startActivity(intent)
            }else{
                ToastUtils.showShort("本机未安装微信")
            }
        }
    }
}