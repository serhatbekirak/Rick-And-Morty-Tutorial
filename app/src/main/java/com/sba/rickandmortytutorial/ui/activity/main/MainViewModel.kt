package com.sba.rickandmortytutorial.ui.activity.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sba.rickandmortytutorial.R
import com.sba.rickandmortytutorial.base.BaseViewModel
import com.sba.rickandmortytutorial.data.ApiStatus
import com.sba.rickandmortytutorial.data.SBAMessage
import com.sba.rickandmortytutorial.data.callback.IPaginationListener
import com.sba.rickandmortytutorial.data.enums.ToastType
import com.sba.rickandmortytutorial.data.model.Character
import com.sba.rickandmortytutorial.data.repository.CharacterRepository
import com.sba.rickandmortytutorial.utils.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val repository: CharacterRepository,
  private val resourcesProvider: ResourcesProvider
) : BaseViewModel(), IPaginationListener {

  //region public liveData
  val characterList = MutableLiveData<List<Character>>()
  //endregion

  //region override properties
  override var currentPageIndex: Int = 1
  override var hasMorePage: Boolean = false
  override var onLoadMoreAction: () -> Unit = { retrieveItems() }
  //endregion

  //region lifecycle
  override fun onCreate(owner: LifecycleOwner) {
    super.onCreate(owner)

    showLoader()
    retrieveItems()
  }
  //endregion

  //region view → vm methods (public)

  fun onSwipeLike(cardCount: Int) {
    if (cardCount < 3 && hasMorePage) retrieveItems()
  }

  fun onSwipeNope(cardCount: Int) {
    if (cardCount < 3 && hasMorePage) retrieveItems()
  }
  //endregion

  //region private methods
  private fun retrieveItems() = viewModelScope.launch {
    repository.getCharacterList(currentPageIndex, resourcesProvider.context).collect { values ->
      when (values.status) {
        ApiStatus.SUCCESS -> {
          values.data?.apply {
            characterList.value = results
            if (info?.next == null) {
              hasMorePage = false
            } else {
              hasMorePage = true
              currentPageIndex++
            }
          }
        }

        ApiStatus.ERROR -> {
          message.value = SBAMessage(resourcesProvider.getString(R.string.error_message), ToastType.Error)
        }
      }
      hideLoader()
    }
  }
  //endregion
}