package com.sba.rickandmortytutorial.data.network

import android.content.Context
import com.sba.rickandmortytutorial.data.SBAResult
import com.sba.rickandmortytutorial.utils.InternetConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

const val NETWORK_MESSAGE = "No Internet Connection"

inline fun <reified T> toResultFlow(ctx: Context, crossinline call: suspend () -> Response<T>?): Flow<SBAResult<T>> {
  return flow {
    val isInternetConnected = InternetConnection.hasInternetConnection(ctx)
    if (isInternetConnected) {
      val c = call()
      c?.let { response ->
        try {
          if (c.isSuccessful && c.body() != null) {
            c.body()?.let {
              emit(SBAResult.Success(it))
            }
          } else {
            val model = setResponseStatus<T>(T::class.java.getDeclaredConstructor().newInstance(), response.message())
            emit(SBAResult.Error(model, response.message()))
          }
        } catch (e: Exception) {
          val model = setResponseStatus<T>(T::class.java.getDeclaredConstructor().newInstance(), e.message)
          emit(SBAResult.Error(model, e.toString()))
        }
      }
    } else {
      val model = setResponseStatus<T>(T::class.java.getDeclaredConstructor().newInstance(), NETWORK_MESSAGE)
      emit(SBAResult.Error(model, NETWORK_MESSAGE))
    }
  }.flowOn(Dispatchers.IO)
}

inline fun <reified T> setResponseStatus(instance: T?, message: String?): T? {
  return try {
    instance?.let {
      val properties = it::class.memberProperties
      for (property in properties) {
        if (property is KMutableProperty<*>) {
          when (property.name) {
            "ErrorCode" -> (property as KMutableProperty<*>).setter.call(instance)
            "Message" -> (property as KMutableProperty<*>).setter.call(instance, message)
          }
        }
      }
    }
    instance
  } catch (e: Exception) {
    null
  }
}