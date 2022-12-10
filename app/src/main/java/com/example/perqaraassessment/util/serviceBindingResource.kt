package com.example.perqaraassessment.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.emitAll

inline fun <ResponseType, RequestType> serviceBindingResource(
    crossinline query: () -> Flow<ResponseType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResponseType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Result.Loading())
        try {
            saveFetchResult(fetch())
            query().map { response ->
                Result.Success(response)
            }
        } catch (throwable: Throwable) {
            query().map { response ->
                Result.Error(response)
            }
        }
    } else {
        query().map { Result.Success(it) }
    }
    emitAll(flow)
}