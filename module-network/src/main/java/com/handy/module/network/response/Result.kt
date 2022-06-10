package com.handy.module.network.response

/**
 * @author: handy
 * @date: 2022-06-08
 * @description:
 */
class Result<T>(val errCode: Int,
                val errMsg: String? = null,
                val data: T? = null) {

    companion object {

        @JvmStatic
        fun <T> success(data: T): Result<T> {
            return Result(0, null, data)
        }

        @JvmStatic
        fun <T> failed(errCode: Int, errMsg: String, data: T?): Result<T> {
            return Result(errCode, errMsg, data)
        }

    }

    override fun toString(): String {
        return "Result(errCode=$errCode, errMsg=$errMsg, data=$data)"
    }

}