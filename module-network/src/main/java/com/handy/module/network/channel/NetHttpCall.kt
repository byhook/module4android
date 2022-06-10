package com.handy.module.network.channel

import com.handy.module.network.NetCall
import com.handy.module.network.response.Result
import com.handy.module.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @author: handy
 * @date: 2022-06-08
 * @description:
 */
class NetHttpCall(private val okHttpClient: OkHttpClient) : NetCall {

    companion object {
        private const val TAG = "NetHttpCall"
    }

    override fun <T> flowFetch(clazz: Class<T>): Flow<Result<T>> {
        return callbackFlow {
            val request = Request.Builder()
                .url("https://www.baidu.com/")
                .build()
            GlobalScope.launch(Dispatchers.Default){
                val response = okHttpClient.newCall(request).execute()
                LogUtils.d(TAG,"response.body:${response.body?.string()} ${Thread.currentThread()}")
            }
            awaitClose {

            }
        }
    }

    override fun cancel() {

    }

}