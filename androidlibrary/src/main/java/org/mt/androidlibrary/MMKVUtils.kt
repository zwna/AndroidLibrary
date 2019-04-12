package org.mt.androidlibrary

import com.tencent.mmkv.MMKV

/**
 *@Description:MMKV的工具类
 *@Author:liubiao
 *@Date:2019/4/12
 */
class MMKVUtils {


    companion object{

        /**
         * 获取单进程安全的MMKV对象
         */
        fun getMMKVBySingleThreaded() = MMKV.defaultMMKV()

        /**
         * 创建指定ID的MMKV对象
         */
        fun getMMKVByDesignateId(mmkvId:String) = MMKV.mmkvWithID(mmkvId)
    }
}