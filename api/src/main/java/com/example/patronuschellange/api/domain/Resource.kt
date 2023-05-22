package com.example.patronuschellange.api.domain

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String?, val data: T? = null) : Resource<Nothing>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
}