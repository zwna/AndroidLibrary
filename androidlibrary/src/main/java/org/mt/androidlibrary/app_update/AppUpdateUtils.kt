package org.mt.androidlibrary.app_update

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.palette.graphics.Palette
import com.blankj.utilcode.util.ToastUtils
import com.king.app.updater.AppUpdater
import com.king.app.updater.callback.UpdateCallback
import org.mt.androidlibrary.R
import org.mt.androidlibrary.custom_view.NumberProgressBar
import java.io.File

/**
 *@Description:App更新的工具类
 *@Author:zwna
 *@Date:2019/3/26
 */
class AppUpdateUtils private constructor(){


    companion object {
        //默认的主色调
        private var defaultThemeColor = Color.parseColor("#e94339")

        //更新APP对话框的顶部图片
        private var bitmapTop: Bitmap? = null

        //设置更新APP对话框的顶部图片
        fun setBitmapTop(bitmap: Bitmap) {
            bitmapTop = bitmap
        }

        /**
         * 下载和安装新版APP
         * @param context 上下文对象
         * @param appUpdateLog APP更新日志
         * @param isForce 是否强制更新
         * @param newApkDownloadUrl 新版本APP的APK的下载地址
         * @param downloadTitle 升级对话框的标题
         * @param updateBtntext 更新APP对话框上按钮的文字
         * @param icon 下载新版APP时的通知栏的图标
         */
        fun updateAppVersion(context: Context,appUpdateLog: String,isForce: Boolean,newApkDownloadUrl: String,downloadTitle: String,updateBtntext: String,@DrawableRes icon: Int) {
            if (bitmapTop == null) {
                bitmapTop = BitmapFactory.decodeResource(context.resources, R.mipmap.lib_update_app_top_bg)
            } else {
                val generate = Palette.from(bitmapTop!!).generate()
                val dominantColor = generate.getDominantColor(defaultThemeColor)
                defaultThemeColor = dominantColor
            }
            val appUpdateDialogView = View.inflate(context, R.layout.dialog_app_update_layout, null)
            val btn_ok = appUpdateDialogView.findViewById<Button>(R.id.btn_ok)
            val iv_close = appUpdateDialogView.findViewById<ImageView>(R.id.iv_close)
            val iv_top = appUpdateDialogView.findViewById<ImageView>(R.id.iv_top)
            val npb = appUpdateDialogView.findViewById<NumberProgressBar>(R.id.npb)
            val tv_update_info = appUpdateDialogView.findViewById<TextView>(R.id.tv_update_info)
            val tv_title = appUpdateDialogView.findViewById<TextView>(R.id.tv_title)
            val line = appUpdateDialogView.findViewById<View>(R.id.line)
            btn_ok.setBackgroundColor(defaultThemeColor)
            btn_ok.text = updateBtntext
            npb.setProgressTextColor(defaultThemeColor)
            npb.reachedBarColor = defaultThemeColor
            iv_top.setImageBitmap(bitmapTop)
            tv_update_info.text = appUpdateLog
            tv_title.text = downloadTitle
            val appCompatDialog = AlertDialog.Builder(context, R.style.UpdateAppDialog)
                .setCancelable(true)
                .setView(appUpdateDialogView)
                .create()
            if (isForce) {
                iv_close.visibility = View.GONE
                line.visibility = View.GONE
                appCompatDialog.setCanceledOnTouchOutside(false)
                appCompatDialog.setCancelable(false)
            } else {
                iv_close.visibility = View.VISIBLE
                line.visibility = View.VISIBLE
                appCompatDialog.setCanceledOnTouchOutside(true)
                appCompatDialog.setCancelable(true)
            }
            appCompatDialog.show()
            iv_close.setOnClickListener { v -> appCompatDialog.dismiss() }
            btn_ok.setOnClickListener { v ->
                AppUpdater.Builder()
                    .serUrl(newApkDownloadUrl)
                    .setReDownload(true)
                    .setShowPercentage(true)
                    .setNotificationIcon(icon)
                    .setInstallApk(true)
                    .build(context)
                    .setUpdateCallback(object : UpdateCallback {

                        override fun onDownloading(isDownloading: Boolean) {
                            if (isDownloading) {
                                ToastUtils.showShort("已经在下载中,请勿重复下载。")
                            }
                        }

                        override fun onStart(url: String) {
                            npb.progress = 0
                            npb.visibility = View.VISIBLE
                        }

                        override fun onProgress(progress: Int, total: Int, isChange: Boolean) {
                            if (isChange) {
                                npb.max = total
                                npb.progress = progress
                            }
                        }

                        override fun onFinish(file: File) {
                            npb.visibility = View.GONE
                            appCompatDialog.dismiss()
                        }

                        override fun onError(e: Exception) {
                            npb.visibility = View.GONE
                        }

                        override fun onCancel() {
                            npb.visibility = View.GONE
                        }
                    })
                    .start()
            }

        }
    }



}