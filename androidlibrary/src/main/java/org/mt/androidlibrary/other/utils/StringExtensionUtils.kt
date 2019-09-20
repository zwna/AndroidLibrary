package org.mt.androidlibrary.other.utils


/**
 *@Description:com.blankj.utilcode.util.StringUtils的扩展工具类
 *@Author:zwna
 *@Date:2019-05-17
 */
class StringExtensionUtils {


    companion object{

        /**
         * 判断一串字符串是否全是数字
         * @return true:全是由数字组成 false:不是
         */
        fun isAllNumberic(str:String):Boolean{
            var i = str.length
            while (--i >= 0) {
                if (!Character.isDigit(str[i])) {
                    return false
                }
            }
            return true
        }

        /**
         * 判断字符串是否是全由英文字母组成
         * @return true:全是由英文字母组成 false:不是
         */
        fun isAllEnglish(fstrData: String): Boolean {
            val c = fstrData.toCharArray()
            for( item in c){
                if(!(item in 'a'..'z' || item in 'A'..'Z')){
                    return false
                }
            }
            return true
        }



    }
}