package com.hellodiffa.coroutinesxroom.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.hellodiffa.coroutinesxroom.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay

/*
* created by Diffa
*/
fun <L, R> resultLiveData(
    databaseQuery: suspend () -> LiveData<L>,
    networkCall: suspend () -> Result<R>,
    saveCallResult: suspend (R) -> Unit,
    io: CoroutineDispatcher
): LiveData<Result<L>> =
    liveData(io) {
        emit(Result.loading<L>())
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
                emit(Result.error<L>(responseStatus.message))
            }
            emitSource(source)
        }
    }


