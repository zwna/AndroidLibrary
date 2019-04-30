package org.mt.androidlibrary.callback

import android.text.Editable
import android.text.TextWatcher

/**
 *@Description:TextWatcher的子类,当用户addTextWatcher的时候不用再必须重写三个方法
 *@Author:zwna
 *@Date:2019-04-30
 */
class SimpleTextWatcher: TextWatcher {


    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

}