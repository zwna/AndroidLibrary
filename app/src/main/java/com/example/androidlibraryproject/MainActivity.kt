package com.example.androidlibraryproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.*
import org.mt.androidlibrary.SystemActivityUtils
import org.mt.androidlibrary.app_update.AppUpdateUtils
import org.mt.androidlibrary.toast.custom_toast.Toasty

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToastNormalNoIcon(view: View){
        Toasty.normal(this@MainActivity,"这是正常的且没有图标的Toast").show()
    }

    fun showToastNormalWithIcon(view:View){
        Toasty.normal(this@MainActivity,"这是正常的且有图标的Toast",resources.getDrawable(R.mipmap.lock)).show()
    }

    fun showToastError(view:View){
        Toasty.error(this@MainActivity,"这是一个提示错误的Toast").show()
    }

    fun showToastCorrect(view:View){
        Toasty.success(this@MainActivity,"这是一个提示成功的Toast").show()
    }

    fun showToastWarning(view:View){
        Toasty.info(this@MainActivity,"这是一个警告的Toast").show()
    }

    fun showToastCustom(view: View){
        Toasty.custom(this@MainActivity,"这是一个完全自定义的Toast",R.mipmap.lock, R.color.colorPrimary,Toast.LENGTH_LONG,true,true).show()
    }

    fun updateApp(view:View){
        AppUpdateUtils.updateAppVersion(this@MainActivity,"1.测试安装\n2.修复BUG\n3.优化体验",false,"http://ateduoduo.118.easysoft168.com/Uploads/apk/atdd_android_4.apk","更新日志","升级",R.mipmap.lock)
    }

    fun setNavBarVisibility(view:View){
      if(BarUtils.isNavBarVisible(this@MainActivity)){
          ToastUtils.showShort("底部导航栏可见")
          BarUtils.setNavBarVisibility(this@MainActivity,false)
      }else{
          ToastUtils.showShort("底部导航栏不可见")
          BarUtils.setNavBarVisibility(this@MainActivity,true)
      }
    }

    fun startCall(view:View){
        if(PermissionUtils.isGranted(android.Manifest.permission.CALL_PHONE)) {
            SystemActivityUtils.startCallActivity("17660160209")
        }
    }

    fun startDial(view:View){
        SystemActivityUtils.startDialActivity()
    }

    fun startDialWithPhoneNumber(view: View){
        SystemActivityUtils.startDialActivityWithPhoneNumber("17660160209")
    }

    fun startSystemBrowser(view:View){
        SystemActivityUtils.startSystemBrowserActivity("https://blog.csdn.net/lovemark8/article/details/40583953")
    }

    fun startApplicationDetail(view:View){
        SystemActivityUtils.startApplicationDetailActivity()
    }

    fun startSetting(view:View){
        SystemActivityUtils.startSettingActivity()
    }

    fun sendSms(view:View){
        SystemActivityUtils.startSmsActivity("17660160209","要发送的短信内容")
    }

    fun startSms(view:View){
        SystemActivityUtils.startSmsActivity("短信内容")
    }

    fun sendEmail(view:View){
        SystemActivityUtils.startSendEmailActivity("3123848646@qq.com")
    }

    fun startSecondActivity(view:View){
        if(!ActivityUtils.isActivityExists(AppUtils.getAppPackageName(),"SecondActivity")) {
            ActivityUtils.startActivity(SecondActivity::class.java)
        }else{
            ToastUtils.showShort("SecondActivity不存在")
        }
    }


}
