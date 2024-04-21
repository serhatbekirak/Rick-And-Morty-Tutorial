package com.sba.rickandmortytutorial.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProvider @Inject constructor(
    @ApplicationContext val context: Context
) {
  fun getString(@StringRes resId: Int): String {
    return context.getString(resId)
  }

  fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
    return context.getString(resId, *formatArgs)
  }

  fun getDrawable(@DrawableRes resId: Int): Drawable? {
    return context.theme?.getDrawable(resId)
  }

  fun getColor(@ColorRes resId: Int): Int {
    return context.getColor(resId)
  }
}