package com.example.androidlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.mt.androidlibrary.ActivityUtils
import org.mt.androidlibrary.BarUtils
import org.mt.androidlibrary.appupdate.AppUpdateUtils
import org.mt.androidlibrary.toast.custom_toast.Toasty
import org.mt.androidlibrary.toast.native_toast.NativeToastUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text_statusBarHeight.text = "${BarUtils.getStatusBarHeight()}px"
        text_actionBarHeight.text = "${BarUtils.getActionBarHeight()}px"
        text_navigationBarHeight.text = "${BarUtils.getBottomNavigationHeight()}px"
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
          NativeToastUtils.showShortToast("底部导航栏可见")
          BarUtils.setNavBarVisibility(this@MainActivity,false)
          text_navigationBarHeight.text = "${BarUtils.getBottomNavigationHeight()}px"
      }else{
          NativeToastUtils.showShortToast("底部导航栏不可见")
          BarUtils.setNavBarVisibility(this@MainActivity,true)
          text_navigationBarHeight.text = "${BarUtils.getBottomNavigationHeight()}px"
      }
    }

    fun startSystemBrowser(view:View){
        ActivityUtils.startSystemBrowserActivity(this,"https://blog.csdn.net/lovemark8/article/details/40583953")
    }
}
