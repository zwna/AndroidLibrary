package org.mt.androidlibrary.appupdate

import java.io.File

/**
 * @author Jenly [Jenly](mailto:jenly1314@gmail.com)
 */
internal interface UpdateCallback {

    /**
     * 最开始调用(在onStart之前调用)
     * @param isDownloading true 表示已经在下载，false表示准备刚调用下载
     */
    fun onDownloading(isDownloading: Boolean)

    /**
     * 开始
     */
    fun onStart(url: String)

    /**
     * 加载进度…
     * @param progress
     * @param total
     * @param isChange 进度百分比是否有改变，（主要可以用来过滤无用的刷新，从而降低刷新频率）
     */
    fun onProgress(progress: Int, total: Int, isChange: Boolean)

    /**
     * 完成
     * @param file
     */
    fun onFinish(file: File)

    /**
     * 错误
     * @param e
     */
    fun onError(e: Exception)


    /**
     * 取消
     */
    fun onCancel()
}
