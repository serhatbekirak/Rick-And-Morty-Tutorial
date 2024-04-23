package com.sba.rickandmortytutorial.data.callback

import com.sba.rickandmortytutorial.ui.activity.main.views.ItemSwipeCardView

interface ISwipeCardCallback {
  fun onSwipeLeftOut(view: ItemSwipeCardView)
  fun onSwipeRightOut(view: ItemSwipeCardView)
}