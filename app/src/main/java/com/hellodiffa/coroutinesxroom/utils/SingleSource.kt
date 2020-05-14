package com.hellodiffa.coroutinesxroom.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.hellodiffa.coroutinesxroom.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
/*
* created by Diffa
*/
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        delay(1_500)
        val source = databaseQuery.invoke().map {
            Result.success(it)
        }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            responseStatus.data?.let { saveCallResult(it) }
        } else if (responseStatus.status == Result.Status.ERROR) {
            if (responseStatus.message != null) {
                emit(Result.error<T>(responseStatus.message))
            }
            emitSource(source)
        }
    }

