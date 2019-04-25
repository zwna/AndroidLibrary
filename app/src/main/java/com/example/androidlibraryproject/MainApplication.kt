package com.example.androidlibraryproject

import android.app.Application
import org.mt.androidlibrary.AndroidLibrary

/**
 *@Description:
 *@Author:dalao
 *@Date:2019/3/22
 */
class MainApplication:Application() {


    override fun onCreate() {
        super.onCreate()
        AndroidLibrary.init(this,null)
    }
}