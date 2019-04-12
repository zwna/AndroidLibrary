package org.mt.androidlibrary.appupdate

/**
 * @author Jenly [Jenly](mailto:jenly1314@gmail.com)
 */
internal abstract class AppUpdateCallback : UpdateCallback {
    override fun onDownloading(isDownloading: Boolean) {

    }

    override fun onStart(url: String) {

    }

    override fun onError(e: Exception) {

    }

    override fun onCancel() {

    }
}
