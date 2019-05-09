package com.example.androidlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ZipUtils
import java.io.File

class FourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)


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
