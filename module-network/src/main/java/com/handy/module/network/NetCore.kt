package com.handy.module.network

import com.handy.module.network.channel.NetHttpCall
import com.handy.module.network.request.NetRequest
import okhttp3.OkHttpClient

/**
 * @author: handy
 * @date: 2022-06-08
 * @description:
 */
class NetCore {

    companion object {
        private val okHttpClient = OkHttpClient()

        @JvmStatic
        fun newCall(url: String, request: NetRequest): NetCall {
            return NetHttpCall(okHttpClient)
        }

    }


}