package com.handy.module.network

import com.handy.module.network.response.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author: handy
 * @date: 2022-06-08
 * @description:
 */
interface NetCall {

    fun <T> flowFetch(clazz: Class<T>): Flow<Result<T>>

    fun cancel()

}