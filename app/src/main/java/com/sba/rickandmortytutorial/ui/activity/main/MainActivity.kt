package com.sba.rickandmortytutorial.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sba.rickandmortytutorial.R
import com.sba.rickandmortytutorial.base.BaseActivity
import com.sba.rickandmortytutorial.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

  // region private properties
  // endregion

  // region override properties
  override val viewModel: MainViewModel by viewModels()
  override val layoutId: Int = R.layout.activity_main
  // endregion

  // region override methods
  override fun initViewModel(savedInstanceState: Bundle?) {
    binding.viewModel = viewModel
  }

  override fun configureUI(savedInstanceState: Bundle?) {
    super.configureUI(savedInstanceState)

    //setStatusBarColorTransparent()
  }
  // endregion

  // region private methods
  // endregion
}