package com.handy.module.network.response

/**
 * @author: handy
 * @date: 2022-06-07
 * @description:
 */
class ResultException(
        val errorCode: Int,
        val errorMsg: String? = ""
) : RuntimeException(errorMsg) {

    override fun toString(): String {
        return "ResultException(errorCode=$errorCode, errorMsg=$errorMsg)"
    }

}