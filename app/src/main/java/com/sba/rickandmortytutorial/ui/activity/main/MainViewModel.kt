package com.sba.rickandmortytutorial.ui.activity.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sba.rickandmortytutorial.R
import com.sba.rickandmortytutorial.base.BaseViewModel
import com.sba.rickandmortytutorial.data.ApiStatus
import com.sba.rickandmortytutorial.data.SBAMessage
import com.sba.rickandmortytutorial.data.SBAResult
import com.sba.rickandmortytutorial.data.enums.ToastType
import com.sba.rickandmortytutorial.data.model.CharacterWrapper
import com.sba.rickandmortytutorial.data.repository.CharacterRepository
import com.sba.rickandmortytutorial.utils.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val repository: CharacterRepository,
  private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {

  //region private liveData
  private val _characterList: MutableLiveData<SBAResult<CharacterWrapper>> = MutableLiveData()
  //endregion

  //region public liveData
  val characterList: LiveData<SBAResult<CharacterWrapper>> = _characterList
  //endregion

  //region lifecycle
  override fun onCreate(owner: LifecycleOwner) {
    super.onCreate(owner)

    showLoader()
    getCharacterList()
  }
  //endregion

  //region view → vm methods (public)
  //endregion

  //region private methods
  private fun getCharacterList() = viewModelScope.launch {
    repository.getCharacterList(1, resourcesProvider.context).collect { values ->
      when (values.status) {
        ApiStatus.SUCCESS -> _characterList.value = values
        ApiStatus.ERROR -> message.value =
          SBAMessage(resourcesProvider.getString(R.string.error_message), ToastType.Error)
      }
      hideLoader()
    }
  }
  //endregion
}