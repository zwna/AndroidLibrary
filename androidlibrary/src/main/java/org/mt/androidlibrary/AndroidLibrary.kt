package org.mt.androidlibrary

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 *@Description:
 *@Author:zwna
 *@Date:2019/4/15
 */
class AndroidLibrary {

    companion object{

        /**
         * 用来进行全局初始化
         * @param applicationContext 上下文对象
         * @param mmkvRootDir MMKV数据保存的文件
         */
        fun init(applicationContext:Application,mmkvRootDir:String?){
            if(mmkvRootDir != null){
                MMKV.initialize(mmkvRootDir)
            }else{
                MMKV.initialize(applicationContext)
            }
        }
    }
}