package org.mt.androidlibrary;

import android.content.Context;


/**
 *@Description:尺寸相关的工具类
 *@Author:zwna
 *@Date:2019/4/12
 */
public class SizeUtils {

    /**
     * dp转换成px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}