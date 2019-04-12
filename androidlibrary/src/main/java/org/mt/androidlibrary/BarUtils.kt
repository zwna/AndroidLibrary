package org.mt.androidlibrary

import android.app.Activity
import android.content.res.Resources
import android.util.TypedValue
import android.view.Window
import androidx.annotation.NonNull
import android.view.ViewGroup
import android.os.Build
import android.view.View




/**
 *@Description:栏相关的工具类
 *@Author:zwna
 *@Date:2019/3/21
 */
class BarUtils private constructor(){

    companion object {

        /**
         * 获取状态栏的高度
         * @return 状态栏高度 单位：像素
         */
        fun getStatusBarHeight():Int{
            val resources = Resources.getSystem()
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }

        /**
         * 获取ActionBar高度
         * @return ActionBar高度 单位：像素
         */
        fun getActionBarHeight():Int{
            val typedValue = TypedValue()
            val application = CommonUtils.getApplicationByReflect()
            if(application != null){
                val b = application.theme?.resolveAttribute(android.R.attr.actionBarSize,typedValue,true)
                if(b != null){
                    return TypedValue.complexToDimensionPixelSize(typedValue.data, application.resources.displayMetrics)
                }
            }
            return 0
        }

        /**
         * 获取底部导航栏的高度
         * @return 底部导航栏的高度 单位：像素
         */
        fun getBottomNavigationHeight():Int{
            val res:Resources = Resources.getSystem()
            val resourceId:Int = res.getIdentifier("navigation_bar_height", "dimen", "android")
            return if(resourceId != 0){
                res.getDimensionPixelSize(resourceId)
            }else{
                0
            }
        }

        /**
         * 设置底部导航栏(虚拟按键)是否可见
         * @param activity
         * @param isVisible 设置为是否可见 true:设置为可见 false:设置为不可见
         */
        fun setNavBarVisibility(activity:Activity,isVisible:Boolean){
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
            setNavBarVisibility(activity.window, isVisible)
        }

        private fun setNavBarVisibility(@NonNull window:Window,isVisible:Boolean){
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
            val decorView = window.decorView as ViewGroup
            var i = 0
            val count = decorView.childCount
            while (i < count) {
                val child = decorView.getChildAt(i)
                val id = child.id
                if (id != View.NO_ID) {
                    val resourceEntryName = CommonUtils.getApplicationByReflect()?.resources?.getResourceEntryName(id)
                    if ("navigationBarBackground" == resourceEntryName) {
                        child.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
                    }
                }
                i++
            }
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            if (isVisible) {
                decorView.systemUiVisibility = decorView.systemUiVisibility and uiOptions.inv()
            } else {
                decorView.systemUiVisibility = decorView.systemUiVisibility or uiOptions
            }
        }

        /**
         * 获取底部导航栏(虚拟按键)是否可见
         * @param activity
         */
        fun isNavBarVisible(@NonNull activity: Activity) = isNavBarVisible(activity.window)

        private fun isNavBarVisible(@NonNull window:Window):Boolean{
            var isVisible = false
            val decorView = window.decorView as ViewGroup
            var i = 0
            val count = decorView.childCount
            while (i < count) {
                val child = decorView.getChildAt(i)
                val id = child.id
                if (id != View.NO_ID) {
                    val resourceEntryName = CommonUtils.getApplicationByReflect()?.resources?.getResourceEntryName(id)
                    if ("navigationBarBackground" == resourceEntryName && child.visibility == View.VISIBLE) {
                        isVisible = true
                        break
                    }
                }
                i++
            }
            if (isVisible) {
                val visibility = decorView.systemUiVisibility
                isVisible = visibility and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION == 0
            }
            return isVisible
        }

    }
}