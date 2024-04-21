package com.sba.rickandmortytutorial.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.setImage(path: String?, placeholderResId: Int = -1) {
  if (path.isNullOrEmpty()) {
    setImageDrawable(null)
  } else {
    Glide.with(this)
      .load(path)
      .placeholder(placeholderResId)
      .into(this)
  }
}