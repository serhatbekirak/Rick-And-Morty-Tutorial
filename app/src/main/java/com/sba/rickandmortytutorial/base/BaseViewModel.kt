package com.sba.rickandmortytutorial.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sba.rickandmortytutorial.data.SBAMessage

abstract class BaseViewModel : ViewModel(), IBaseViewModel {

  override val appBarTitle = MutableLiveData<String>()

  override val message = MutableLiveData<SBAMessage>()
  override val isLoading = MutableLiveData<Boolean>()

  override fun showLoader() {
    isLoading.value = true
  }

  override fun hideLoader() {
    isLoading.value = false
  }
}