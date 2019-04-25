package com.example.androidlibraryproject

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ActivityUtils
import kotlinx.android.synthetic.main.activity_second.*
import org.mt.androidlibrary.WebViewUtils
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebViewUtils.enableSlowWholeDocumentDraw()
        setContentView(R.layout.activity_second)
        webView.loadUrl("http://www.baidu.com")
        webView.webViewClient = WebViewClient()

        numberProgressBar.max = 100
        numberProgressBar.progress = 40
        numberProgressBar.progressTextSize = 18f
        numberProgressBar.prefix = "%"
        numberProgressBar.suffix = "进度"
        numberProgressBar.reachedBarColor = ContextCompat.getColor(this,R.color.colorAccent)
        numberProgressBar.unreachedBarColor = ContextCompat.getColor(this,android.R.color.holo_green_light)
    }


    fun captureAllContent(view: View){
        saveBitmap(WebViewUtils.getAllContentScreenShot(webView))
    }

    fun startThirdActivity(view:View){
        ActivityUtils.startActivity(ThirdActivity::class.java)
    }


    // 指纹图片存放路径
     val sdCardDir = "${Environment.getExternalStorageDirectory()}/fingerprintimages/"

    /**
     * 保存指纹图片
     *
     * @param bitmap
     */
    private fun saveBitmap(bitmap: Bitmap) {
        var  file:File? = null
        try {
            val dirFile =  File(sdCardDir)
            if (!dirFile.exists()) {              //如果不存在，那就建立这个文件夹
                dirFile.mkdirs()
            }
            file =  File(sdCardDir,  "1.jpg")
            val fos =  FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: Throwable) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.contentResolver,
                file?.absolutePath, "1.jpg", null)
        } catch (e:FileNotFoundException) {
            e.printStackTrace()
        }
        // 通知图库更新
        sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
            Uri.parse("file://" + "/sdcard/namecard/")))

    }
}
