package com.sba.rickandmortytutorial.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sba.rickandmortytutorial.R
import com.sba.rickandmortytutorial.base.BaseActivity
import com.sba.rickandmortytutorial.data.callback.ISwipeCardCallback
import com.sba.rickandmortytutorial.data.model.Character
import com.sba.rickandmortytutorial.databinding.ActivityMainBinding
import com.sba.rickandmortytutorial.ui.activity.main.views.CharacterCardView
import com.sba.rickandmortytutorial.ui.activity.main.views.ItemSwipeCardView
import com.sba.rickandmortytutorial.utils.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), ISwipeCardCallback {

  // region private properties
  // endregion

  // region override properties
  override val viewModel: MainViewModel by viewModels()
  override val layoutId: Int = R.layout.activity_main
  // endregion

  // region override methods
  override fun initViewModel(savedInstanceState: Bundle?) {
    binding.vm = viewModel
  }

  override fun configureUI(savedInstanceState: Bundle?) {
    super.configureUI(savedInstanceState)

  }

  override fun observeViewModel() {
    super.observeViewModel()

    observe(viewModel.characterList, ::updateCharacterList)
  }
  // endregion

  // region private methods
  private fun updateCharacterList(characterList: List<Character>) {
    characterList.forEach {
      val cardView = ItemSwipeCardView(this, null)
      cardView.configureUI(it, this)
      binding.rlMain.addView(cardView, 0)
    }
  }
  // endregion

  override fun onSwipeLeftOut(view: ItemSwipeCardView) {
    binding.rlMain.apply {
      removeView(view)
      viewModel.onSwipeNope(childCount)
    }
  }

  override fun onSwipeRightOut(view: ItemSwipeCardView) {
    binding.rlMain.apply {
      removeView(view)
      viewModel.onSwipeLike(childCount)
    }
  }
}