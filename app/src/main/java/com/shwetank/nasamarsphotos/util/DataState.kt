package com.shwetank.nasamarsphotos.util


sealed class DataState<out T> {

    data class Success<out V>(val data: V): DataState<V>()
    data class Error(val exception: Exception): DataState<Nothing>()
    object Loading: DataState<Nothing>()

}