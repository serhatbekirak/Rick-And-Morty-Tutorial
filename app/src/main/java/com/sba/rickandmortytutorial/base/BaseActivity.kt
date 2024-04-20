package com.sba.rickandmortytutorial.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sba.rickandmortytutorial.data.SBAMessage
import com.sba.rickandmortytutorial.views.SBALoader
import com.sba.rickandmortytutorial.utils.extensions.observe
import com.sba.rickandmortytutorial.utils.extensions.showToast

abstract class BaseActivity<VM : IBaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
  private var loaderManager = SBALoader()

  val binding by lazy { DataBindingUtil.setContentView(this, layoutId) as DB }

  abstract val layoutId: Int
  abstract val viewModel: VM

  abstract fun initViewModel(savedInstanceState: Bundle?)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    loaderManager.context = this
    initViewModel(savedInstanceState)
    observeViewModel()
    lifecycle.addObserver(viewModel)
    binding.lifecycleOwner = this

    configureUI(savedInstanceState)
    configureOnClickListener()
  }

  override fun onDestroy() {
    super.onDestroy()

    loaderManager.clearLoader()
  }

  open fun observeViewModel() {
    observe(viewModel.isLoading, ::controlLoader)
    observe(viewModel.message, ::showMessage)
  }
  open fun configureUI(savedInstanceState: Bundle?) {
    //do nothing
  }
  open fun configureOnClickListener() {
    //do nothing
  }

  private fun controlLoader(isLoader: Boolean) {
    if (isLoader) loaderManager.showLoader()
    else loaderManager.hideLoader()
  }

  private fun showMessage(message: SBAMessage?) {
    showToast(message?.getText(this), message?.type)
  }
}