package com.example.androidlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.ArrayMap
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_third.*
import org.mt.androidlibrary.AssetsManageUtils
import org.mt.androidlibrary.MMKVUtils
import org.mt.androidlibrary.net.RetrofitFactory
import org.mt.androidlibrary.net.config.ApiConfig
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
                text_assetsJsonContent.text = Editable.Factory.getInstance().newEditable(json)
            }
        }
    }

    fun saveDataByMMKV(v:View){
        MMKVUtils.getMMKVBySingleThreaded().encode("key","value")
    }

    fun getDataByMMKV(v:View){
        ToastUtils.showShort(MMKVUtils.getMMKVBySingleThreaded().decodeString("key"))
    }

    fun getDataByRetrofit(view:View){
       val commonHeaders =  ArrayMap<String,String>()
        commonHeaders["source"] = "jptcashier"
       val commonParam = HashMap<String,Any>()
        commonParam["appid"] = "1"
        commonParam["PHPSESSID"] = "34u9kb9erk67sclh9a6efntru4"
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
        param.putAll(commonParam as HashMap<String,String>)
        param["api_name"] = "jpt.MarketWallet.withdraw"
        param["bank_card_id"] = "11"
        param["money"] = "10"
        param["pay_password"] = "96e79218965eb72c92a549dd5a330112"
        RetrofitFactory.getInstance(apiConfig).create(Api::class.java).withdraw(param)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<WithdrawBean>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: WithdrawBean) {
                    ToastUtils.showShort(t.data.bank_name)
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort(e.message)
                }

            })
    }
}
