package com.example.androidlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.ArrayMap
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_third.*
import org.mt.androidlibrary.AssetsManageUtils
import org.mt.androidlibrary.MMKVUtils
import org.mt.androidlibrary.net.RetrofitFactory
import org.mt.androidlibrary.net.config.ApiConfig
import org.mt.androidlibrary.rxjava.callback.RxJavaCallBack
import org.mt.androidlibrary.toast.custom_toast.Toasty
import java.util.concurrent.Executors
import io.reactivex.functions.Consumer as Consumer

class ThirdActivity : AppCompatActivity() {

    private val param = HashMap<String,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        Executors.newFixedThreadPool(5).execute{
            val json = AssetsManageUtils.getAssetsJson("area.json",this)
            runOnUiThread {
                  SpanUtils.with(text_assetsJsonContent).append(json).setBoldItalic()
                      .setUnderline()
                      .setStrikethrough()
                      .create()
            }
        }
    }

    fun saveDataByMMKV(v:View){
        MMKVUtils.getMMKVBySingleThreaded().encode("key","value")
    }

    fun getDataByMMKV(v:View){
        ToastUtils.showShort(MMKVUtils.getMMKVBySingleThreaded().decodeString("key"))
    }

    fun startFour(v:View){
        ActivityUtils.startActivity(FourActivity::class.java)
    }

    fun getDataByRetrofit(view:View){
       val commonHeaders =  ArrayMap<String,String>()
        commonHeaders["source"] = "jptcashier"
        commonHeaders["appversion"] = "cashier_" + AppUtils.getAppVersionCode(AppUtils.getAppPackageName())
       val commonParam = HashMap<String,Any>()
        commonParam["appid"] = "1"
        commonParam["PHPSESSID"] = "1111111111"
        commonParam["token"] = "11111111111111111111111111111111"
        val apiConfig = ApiConfig.Builder()
           .setBaseUrl("http://jianpeitong.118.easysoft168.com/")
           .setConnectTimeout(5000)
           .setReadTimeout(5000)
           .setWriteTimeout(5000)
           .setMaxRetry(4)
           .setHeads(commonHeaders)
           .setComnParams(commonParam)
           .setOpenHttps(true)
           .build()
        param["api_name"] = "jpt.MarketWallet.withdraw"
        param["bank_card_id"] = "11"
        param["money"] = "10"
        param["pay_password"] = "96e79218965eb72c92a549dd5a330112"
        RetrofitFactory.getInstance(apiConfig).create(Api::class.java).withdraw(param)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: RxJavaCallBack<WithdrawBean>(){
                override fun onNextResponse(t: WithdrawBean) {
                  if(t.code == 0){
                      Toasty.success(this@ThirdActivity,t.data.opening_bank).show()
                  }else{
                      onRequestError(t)
                  }
                }

                override fun onRequestError(t: WithdrawBean?) {
                    Toasty.error(this@ThirdActivity,t?.error_msg!!).show()
                }

                override fun onFinishResponse() {
                }

            })
    }
}
