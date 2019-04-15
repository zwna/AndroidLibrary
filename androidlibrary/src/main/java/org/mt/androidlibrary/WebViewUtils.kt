package org.mt.androidlibrary

import android.graphics.Bitmap
import android.graphics.Canvas
import android.webkit.WebView

/**
 *@Description:WebView的工具类
 *@Author:zwna
 *@Date:2019/4/15
 */
class WebViewUtils {

    companion object{

        /**
         * 截取WebView展示的全部内容(包含未显示部分)
         * @return 截取成功后的Bitmap图
         */
        fun getAllContentScreenShot(webView: WebView):Bitmap{
            //获取webview缩放率
            val scale = webView.scale
            //得到缩放后webview内容的高度
            val webViewHeight =  (webView.contentHeight * scale).toInt()
            val bitmap = Bitmap.createBitmap(webView.width,webViewHeight,Bitmap.Config.ARGB_8888)
            val canvas =  Canvas(bitmap)
            //绘制
            webView.draw(canvas)
            return bitmap
        }

        /**
         * 截取WebView显示的内容
         */
    }
}