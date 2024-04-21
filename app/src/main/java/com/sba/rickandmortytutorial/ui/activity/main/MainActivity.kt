package com.sba.rickandmortytutorial.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sba.rickandmortytutorial.R
import com.sba.rickandmortytutorial.base.BaseActivity
import com.sba.rickandmortytutorial.data.model.Character
import com.sba.rickandmortytutorial.databinding.ActivityMainBinding
import com.sba.rickandmortytutorial.ui.adapters.SwipeCardAdapter
import com.sba.rickandmortytutorial.utils.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

  // region private properties
  private lateinit var mAdapter: SwipeCardAdapter
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

    mAdapter = SwipeCardAdapter()

    binding.apply {
      rvCharacter.adapter = mAdapter
      rvCharacter.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
      rvCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
          super.onScrolled(recyclerView, dx, dy)

          if (!recyclerView.canScrollHorizontally(1)) {
            viewModel.loadMoreItems()
          }
        }
      })
    }
  }

  override fun observeViewModel() {
    super.observeViewModel()

    observe(viewModel.characterList, ::updateCharacterList)
  }
  // endregion

  // region private methods

  private fun updateCharacterList(characterList: List<Character>) {
    mAdapter.refreshContents(characterList)
  }
  // endregion
}