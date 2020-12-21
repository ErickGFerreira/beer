package com.egferreira.pagtest.base.request

import com.egferreira.pagtest.base.response.Error

class Result<T> private constructor(
    private val success: T?,
    private val failure: Error?
) {
    suspend fun onSuccess(action: suspend (T) -> Unit): Result<T> {
        success?.let { action(it) }
        return this
    }

    suspend fun onFailure(action: suspend (Error) -> Unit): Result<T> {
        failure?.let { action(it) }
        return this
    }

    suspend fun onAny(action: suspend (Result<T>) -> Unit): Result<T> {
        success?.let { action(this) }
        failure?.let { action(this) }
        return this
    }

    suspend fun flatMapError(action: suspend (Error) -> Result<T>): Result<T> {
        if (failure == null) return this
        return action(failure)
    }

    suspend fun mapError(action: suspend (Error) -> Error): Result<T> {
        if (failure == null) return this

        return failure(
            action(failure)
        )
    }

    suspend fun <V> flatMap(action: suspend (T) -> Result<V>): Result<V> {
        if (success == null) return Result(success, failure)
        return action(success)
    }

    suspend fun <V> map(action: suspend (T) -> V): Result<V> {
        if (success == null) return Result(success, failure)

        return success(
            action(success)
        )
    }

    fun getOrNull(): T? = success

    fun exceptionOrNull(): Throwable? = failure

    companion object {
        fun <T> success(data: T) = Result(success = data, failure = null)
        fun <T> failure(error: Error) = Result<T>(success = null, failure = error)
    }
}