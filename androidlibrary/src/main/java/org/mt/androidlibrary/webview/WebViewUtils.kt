package org.mt.androidlibrary.webview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 *@Description:WebView的工具类
 *@Author:zwna
 *@Date:2019/4/15
 */
class WebViewUtils {

    companion object{

        /**
         * Android5.0及以上版本，Android对WebView进行了优化，为了减少内存使用和提高性能，
         * 使用WebView加载网页时只绘制显示部分.该方法可以
         * 让WevView整个界面都显示出来而不是只显示展示出来的部分
         * 在setContentView之前调用
         */
        fun enableSlowWholeDocumentDraw(){
            if(android.os.Build.VERSION.SDK_INT >= 21){
                WebView.enableSlowWholeDocumentDraw()
            }
        }

        /**
         * 截取WebView展示的全部内容(包含未显示部分)
         * 安卓5.0及以上版本
         * 使用该方法之前需要调用{WebViewUtils.enableSlowWholeDocumentDraw()}
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
         * 当拥有WebView的界面被销毁的时候释放WebView
         * @param webView 目标WebView
         */
        fun releaseWebView(webView: WebView){
            webView.clearCache(true)
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            webView.clearHistory()
            webView.removeAllViews()
            webView.destroy()
        }
    }
}