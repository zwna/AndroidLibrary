package com.example.androidlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import org.mt.androidlibrary.MMKVUtils

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    fun saveDataByMMKV(v:View){
        MMKVUtils.getMMKVBySingleThreaded().encode("key","value")
    }

    fun getDataByMMKV(v:View){
        ToastUtils.showShort(MMKVUtils.getMMKVBySingleThreaded().decodeString("key"))
    }

    fun circleImageView(view:View){

    }
}
