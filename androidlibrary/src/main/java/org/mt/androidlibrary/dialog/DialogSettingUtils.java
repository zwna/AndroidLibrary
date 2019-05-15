package org.mt.androidlibrary.dialog;

import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import com.blankj.utilcode.util.SizeUtils;

/**
 * 对原生的AlertDialog进行相关的配置
 */
public class DialogSettingUtils {

        /**
         * 设置AlertDialog的宽度和高度
         * @param alertDialog 目标AlertDialog
         * @param widthPercent 宽度占屏幕宽度的百分比
         * @param heightPercent 高度占屏幕高度的百分比
         * @see Gravity
         */
        public static void setDialogWidthAndHeight(Dialog alertDialog, float widthPercent, float heightPercent){
            setDialogWidthAndHeight(alertDialog, widthPercent, heightPercent, Gravity.CENTER);
        }

        /**
         * 设置AlertDialog的宽度和高度
         * @param alertDialog 目标AlertDialog
         * @param widthPercent 宽度占屏幕宽度的百分比
         * @param heightPercent 高度占屏幕高度的百分比
         * @param alertDialogGravity AlertDialog在屏幕的位置
         * @see Gravity
         */
        public static void setDialogWidthAndHeight(Dialog alertDialog, float widthPercent, float heightPercent, int alertDialogGravity){

            Window window = alertDialog.getWindow();
            WindowManager windowManager =  window.getWindowManager();
            DisplayMetrics displayMetrics = new DisplayMetrics();

            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.width = (int) (displayMetrics.widthPixels * widthPercent);
            windowAttributes.height = (int) (displayMetrics.heightPixels * heightPercent);

            window.setAttributes(windowAttributes);
            window.setGravity(alertDialogGravity);

            alertDialog.onWindowAttributesChanged(windowAttributes);
        }

         /**
          * 设置AlertDialog的宽度和高度为具体的数值
          * @param alertDialog 目标AlertDialog
          * @param width 宽度 单位:dp
          * @param height 高度 单位:dp
          * @param alertDialogGravity AlertDialog在屏幕的位置
          * @see Gravity
          */
         public static void setDialogWidthAndHeightByDp(Dialog alertDialog, float width, float height, int alertDialogGravity){
            Window window = alertDialog.getWindow();
            WindowManager windowManager =  window.getWindowManager();
            DisplayMetrics displayMetrics = new DisplayMetrics();

            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.width = SizeUtils.dp2px(width);
            windowAttributes.height = SizeUtils.dp2px(height);

            window.setAttributes(windowAttributes);
            window.setGravity(alertDialogGravity);

            alertDialog.onWindowAttributesChanged(windowAttributes);
        }

    /**
     * 设置AlertDialog的宽度和高度为具体的数值
     * @param alertDialog 目标AlertDialog
     * @param width 宽度 单位:dp
     * @param height 高度 单位:dp
     */
    public static void setDialogWidthAndHeightByDp(Dialog alertDialog, float width, float height){
       setDialogWidthAndHeightByDp(alertDialog, width, height, Gravity.CENTER);
    }

//    /**
//     * 设置AlertDialog的宽度和高度为具体的数值
//     * @param alertDialog 目标AlertDialog
//     * @param width 宽度 单位:dp
//     * @param height 高度 单位:dp
//     */
//    public static void setAndroidxDialogWidthAndHeightByDp(androidx.appcompat.app.AlertDialog alertDialog, float width, float height){
//       setAndroidxDialogWidthAndHeightByDp(alertDialog,width,height,Gravity.CENTER);
//    }
//
//    /**
//     * 设置AlertDialog的宽度和高度为具体的数值
//     * @param alertDialog 目标AlertDialog
//     * @param width 宽度 单位:dp
//     * @param height 高度 单位:dp
//     * @param alertDialogGravity AlertDialog在屏幕的位置
//     */
//    public static void setAndroidxDialogWidthAndHeightByDp(androidx.appcompat.app.AlertDialog alertDialog, float width, float height, int alertDialogGravity){
//        Window window = alertDialog.getWindow();
//        WindowManager windowManager =  window.getWindowManager();
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//
//        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.width = SizeUtils.dp2px(width);
//        windowAttributes.height = SizeUtils.dp2px(height);
//
//        window.setAttributes(windowAttributes);
//        window.setGravity(alertDialogGravity);
//
//        alertDialog.onWindowAttributesChanged(windowAttributes);
//    }
//
//    /**
//     * 设置AlertDialog的宽度和高度
//     * @param alertDialog 目标AlertDialog
//     * @param widthPercent 宽度占屏幕宽度的百分比
//     * @param heightPercent 高度占屏幕高度的百分比
//     * @param alertDialogGravity AlertDialog在屏幕的位置
//     * @see Gravity
//     */
//    public static void setAndroidxAlertDialogWidthAndHeight(androidx.appcompat.app.AlertDialog alertDialog, float widthPercent, float heightPercent, int alertDialogGravity){
//
//        Window window = alertDialog.getWindow();
//        WindowManager windowManager =  window.getWindowManager();
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//
//        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.width = (int) (displayMetrics.widthPixels * widthPercent);
//        windowAttributes.height = (int) (displayMetrics.heightPixels * heightPercent);
//
//        window.setAttributes(windowAttributes);
//        window.setGravity(alertDialogGravity);
//
//        alertDialog.onWindowAttributesChanged(windowAttributes);
//    }
//
//    /**
//     * 设置AlertDialog的宽度和高度
//     * @param alertDialog 目标AlertDialog
//     * @param widthPercent 宽度占屏幕宽度的百分比
//     * @param heightPercent 高度占屏幕高度的百分比
//     */
//    public static void setAndroidxAlertDialogWidthAndHeight(androidx.appcompat.app.AlertDialog alertDialog, float widthPercent, float heightPercent){
//       setAndroidxAlertDialogWidthAndHeight(alertDialog,widthPercent,heightPercent,Gravity.CENTER);
//    }


}