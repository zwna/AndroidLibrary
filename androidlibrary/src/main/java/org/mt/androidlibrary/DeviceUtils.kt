package org.mt.androidlibrary

import java.io.File


/**
 *@Description:获取设备信息
 *@Author:zwna
 *@Date:2019/4/15
 */
class DeviceUtils {


    companion object{

        /**
         * 判断设备是否被root
         * @return true:被root false:没有被root
         */
        fun isDeviceRooted():Boolean{
            val su = "su"
            val locations = arrayOf(
                "/system/bin/",
                "/system/xbin/",
                "/sbin/",
                "/system/sd/xbin/",
                "/system/bin/failsafe/",
                "/data/local/xbin/",
                "/data/local/bin/",
                "/data/local/",
                "/system/sbin/",
                "/usr/bin/",
                "/vendor/bin/"
            )
            for (location in locations) {
                if (File(location + su).exists()) {
                    return true
                }
            }
            return false
        }
    }
}