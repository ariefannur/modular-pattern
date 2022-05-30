package com.github.ariefannur.modular.core.base

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Flow<Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope = GlobalScope,
        callback: (DataState<Type>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.IO) {
            try {
                callback.invoke(DataState.Loading)
                run(params).collect {
                    callback.invoke(DataState.Success(it))
                }
            } catch (e: Exception) {
                callback.invoke(DataState.Failure(404, e.message ?: ""))
            }

        }
    }

    class None
}