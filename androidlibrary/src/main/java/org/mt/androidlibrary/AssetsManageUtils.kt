package org.mt.androidlibrary

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 *@Description:assets文件夹的工具类
 *@Author:zwna
 *@Date:2019-05-08
 */
class AssetsManageUtils {

    companion object{

        /**
         * 读取assets文件夹下的json文件中的json数据
         * @param fileName assets文件夹下，json文件的名称
         * @param context 上下文对象
         * @return 读取到的json字符串
         */
        fun getAssetsJson(fileName: String, context: Context): String {
            //将json数据变成字符串
            val stringBuilder = StringBuilder()
            try {
                //获取assets资源管理器
                val assetManager = context.assets
                //通过管理器打开文件并读取
                val bf = BufferedReader(
                    InputStreamReader(
                        assetManager.open(fileName)
                    )
                )
                var line: String? = null
                while (bf.readLine().let {
                        line = it
                        line != null }) {
                    stringBuilder.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return stringBuilder.toString()
        }
    }

}