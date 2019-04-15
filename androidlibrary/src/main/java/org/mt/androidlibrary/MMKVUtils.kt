package org.mt.androidlibrary

import com.tencent.mmkv.MMKV

/**
 *@Description:MMKV的工具类
 *@Author:zwna
 *@Date:2019/4/12
 */
class MMKVUtils {

    companion object{

        /**
         * 获取单进程安全的MMKV对象
         */
        fun getMMKVBySingleThreaded() = MMKV.defaultMMKV()

        /**
         * 创建指定ID的MMKV对象,通过该ID来区分存储
         */
        fun getMMKVByDesignateId(mmkvId:String) = MMKV.mmkvWithID(mmkvId)

        /**
         * 创建多进程安全的MMKV对象
         */
        fun getMMKVByMultiProcess(mmkvId: String) = MMKV.mmkvWithID(mmkvId,MMKV.MULTI_PROCESS_MODE)

        /**
         * 是否包含某个key
         */
        fun containerKey(mmkv: MMKV,key:String) = mmkv.containsKey(key)

        /**
         * 移除某个键值对
         */
        fun removeKeyValue(mmkv: MMKV,key: String){
            mmkv.removeValueForKey(key)
        }

        /**
         * 移除多个键值对
         */
        fun removeKeyValues(mmkv: MMKV,keys:Array<String>){
            mmkv.removeValuesForKeys(keys)
        }

        /**
         * 清空全部数据
         */
        fun clearAll(mmkv: MMKV){mmkv.clearAll()}

    }
}