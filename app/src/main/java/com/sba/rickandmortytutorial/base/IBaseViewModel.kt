package com.sba.rickandmortytutorial.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.sba.rickandmortytutorial.data.SBAMessage

interface IBaseViewModel : DefaultLifecycleObserver {
  val appBarTitle: MutableLiveData<String>

  val message: MutableLiveData<SBAMessage>
  val isLoading: MutableLiveData<Boolean>

  fun showLoader()
  fun hideLoader()
}