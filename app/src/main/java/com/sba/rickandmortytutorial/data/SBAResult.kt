package com.sba.rickandmortytutorial.data

sealed class SBAResult<out T>(val status: ApiStatus, val data: T?, val message:String?) {

  data class Success<out T>(val _data: T?): SBAResult<T>(status = ApiStatus.SUCCESS, data = _data, message = null)

  data class Error<out T>(val _data: T?,val exception: String): SBAResult<T>(status = ApiStatus.ERROR, data = _data, message = exception)
}

enum class ApiStatus {
  SUCCESS,
  ERROR
}