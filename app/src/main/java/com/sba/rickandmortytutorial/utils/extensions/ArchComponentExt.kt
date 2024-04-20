package com.sba.rickandmortytutorial.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
  liveData.observe(this, Observer {
    action(it)
  })
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: () -> Unit) {
  liveData.observe(this, Observer {
    action()
  })
}