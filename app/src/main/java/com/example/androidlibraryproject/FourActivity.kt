package com.example.androidlibraryproject

import android.os.Bundle
import android.os.Environment
import android.view.View
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ZipUtils
import org.mt.androidlibrary.base.ui.BaseActivity
import java.io.File

class FourActivity : BaseActivity<com.example.androidlibraryproject.databinding.ActivityFourBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_four
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }


    fun zipFile(v:View){
        val fileByPath =
            FileUtils.getFileByPath("${Environment.getExternalStorageDirectory()}${File.separator}" + "下载")
        val list = listOf(fileByPath.absolutePath)
        ZipUtils.zipFiles(list,"${Environment.getExternalStorageDirectory()}${File.separator}" + "下载.zip")
    }

    fun unZipFile(v:View){
        var fileList = ZipUtils.unzipFile(FileUtils.getFileByPath("${Environment.getExternalStorageDirectory()}${File.separator}" + "下载.zip"),FileUtils.getFileByPath("${Environment.getExternalStorageDirectory()}${File.separator}" + "a"))
        LogUtils.e("fileList",fileList)
    }


}
