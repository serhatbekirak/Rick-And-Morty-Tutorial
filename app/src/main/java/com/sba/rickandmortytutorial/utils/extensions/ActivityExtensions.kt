package com.sba.rickandmortytutorial.utils.extensions

import android.app.Activity
import android.graphics.Color

fun Activity.setStatusBarColorTransparent() {
  window.statusBarColor = Color.TRANSPARENT
}