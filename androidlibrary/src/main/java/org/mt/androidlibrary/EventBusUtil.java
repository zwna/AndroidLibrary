package org.mt.androidlibrary;

import org.greenrobot.eventbus.EventBus;

/**
 *@Description:EventBus的工具类
 *@Author:zwna
 *@Date:2019-05-17
 */
public class EventBusUtil {

    public static void regist(Object o) {
        if (!EventBus.getDefault().isRegistered(o))
            EventBus.getDefault().register(o);
    }

    public static void unRegist(Object o) {
        if (EventBus.getDefault().isRegistered(o))
            EventBus.getDefault().unregister(o);
    }

    public static void post(Object o) {
        EventBus.getDefault().post(o);
    }


    public static void postSticky(Object o) {
        EventBus.getDefault().post(o);
    }

}
