package com.sba.rickandmortytutorial.ui.activity.main

import androidx.lifecycle.LifecycleOwner
import com.sba.rickandmortytutorial.base.BaseViewModel
import com.sba.rickandmortytutorial.data.SBAMessage
import com.sba.rickandmortytutorial.data.enums.ToastType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

  //region public liveData
  //endregion

  //region lifecycle
  override fun onCreate(owner: LifecycleOwner) {
    super.onCreate(owner)

    //showLoader()
    message.value = SBAMessage("Test mesaj", ToastType.Error)
  }
  //endregion

  //region view → vm methods (public)
  //endregion
}