package com.sba.rickandmortytutorial.utils.extensions

import android.content.Context
import com.sba.rickandmortytutorial.data.enums.ToastType
import com.sba.rickandmortytutorial.views.SBAToaster


fun Context.showToast(message: String?, type: ToastType?) {
  if (!message.isNullOrEmpty() && type != null)
    SBAToaster.getInstance(this).showToast(message, type)
}
