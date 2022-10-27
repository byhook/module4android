package com.handy.module.utils

import android.text.TextUtils
import android.util.Log
import com.handy.module.utils.LogUtils

/**
 * @author: handy
 */
object LogUtils {

    @JvmStatic
    fun v(tag: String, content: String) {
        if (!content.isNullOrEmpty()) {
            Log.v(tag, "[${Thread.currentThread()}] $content")
        }
    }

    @JvmStatic
    fun d(tag: String, content: String) {
        if (!content.isNullOrEmpty()) {
            Log.d(tag, "[${Thread.currentThread()}] $content")
        }
    }

    @JvmStatic
    fun e(tag: String, content: String) {
        if (!content.isNullOrEmpty()) {
            Log.e(tag, "[${Thread.currentThread()}] $content")
        }
    }

    @JvmStatic
    fun e(tag: String, throwable: Throwable?) {
        if (throwable != null) {
            e(tag, throwable.toString())
        }
    }
}