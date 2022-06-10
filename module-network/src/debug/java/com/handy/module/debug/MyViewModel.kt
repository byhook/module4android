package com.handy.module.debug

import androidx.lifecycle.ViewModel
import com.handy.module.utils.LogUtils

/**
 * @author: handy
 * @date: 2022-06-08
 * @description:
 */
class MyViewModel : ViewModel() {

    init {
        LogUtils.d("MyViewModel","myViewModel:$this")
    }

}