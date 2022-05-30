package com.github.ariefannur.modular.core.base

sealed class DataState<out T: Any>{
    object Loading: DataState<Nothing>()
    data class Success<T: Any>(val result: T) : DataState<T>()
    data class Failure(val errorCode: Int, val message: String) : DataState<Nothing>()
}