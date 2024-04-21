package com.sba.rickandmortytutorial.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object InternetConnection {
  fun hasInternetConnection(context: Context?): Boolean {
    try {
      if (context == null)
        return false
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
          as ConnectivityManager
      val activeNetwork = connectivityManager.activeNetwork ?: return false
      val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
      return when {
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
      }
    } catch (e: Exception) {
      return false
    }
  }
}