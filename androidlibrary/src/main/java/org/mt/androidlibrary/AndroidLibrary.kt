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