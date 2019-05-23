package org.mt.androidlibrary.base.presenter

import org.mt.androidlibrary.base.view.BaseView

/**
 *@Description:
 *@Author:zwna
 *@Date:2019-05-22
 */
interface BasePresenter {

    /**
     * 绑定
     */
    fun attachView(baseView: BaseView)

    /**
     * 解绑
     */
    fun detachView()

}