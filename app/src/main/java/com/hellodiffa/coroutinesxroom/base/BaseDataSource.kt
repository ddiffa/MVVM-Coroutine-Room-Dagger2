package com.hellodiffa.coroutinesxroom.base

import com.hellodiffa.coroutinesxroom.common.Result
import retrofit2.Response
import timber.log.Timber

/*
* created by Diffa
*/

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error("${response.code()} : ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.error("Network call has failed for a following reason : $message")
    }
}