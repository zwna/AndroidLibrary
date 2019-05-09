package org.mt.androidlibrary

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever

/**
 *@Description:视频相关的工具类
 *@Author:zwna
 *@Date:2019-05-09
 */
class VideoUtils {

    companion object{

        /**
         * 获取到视频第一帧的图片，支持本地视频和网络视频
         * @param videoSource 视频地址
         * @return 视频第一帧的Bitmap图
         */
        fun getVideoFirstFrame(videoSource:String):Bitmap?{
            var bitmap:Bitmap? = null
            val retriever = MediaMetadataRetriever()
            try {
                retriever.setDataSource(videoSource)
                bitmap = retriever.frameAtTime
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } finally {
                retriever.release()
            }
            return bitmap
        }

        /**
         * 获取视频的播放时长
         * @param videoSource 视频地址
         * @return 视频时长 单位：毫秒
         */
        fun getVideoDuration(videoSource:String):String{
            var duration = "0"
            val mmr =  MediaMetadataRetriever()
            try {
                mmr.setDataSource(videoSource)
                duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION) // 播放时长单位为毫秒
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } finally {
                mmr.release()
            }
            return duration
        }


    }

}