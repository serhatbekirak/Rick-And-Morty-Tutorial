package com.sba.rickandmortytutorial.data

import android.content.Context
import com.sba.rickandmortytutorial.data.enums.ToastType

class SBAMessage {
  private var error: SBAException? = null
  private var text: String? = null
  private var textId: Int? = null
  val type: ToastType

  constructor(text: String, type: ToastType) {
    this.text = text
    this.type = type
  }

  constructor(textId: Int, type: ToastType) {
    this.textId = textId
    this.type = type
  }

  constructor(error: SBAException, type: ToastType) {
    this.error = error
    this.type = type
  }

  fun getText(context: Context): String? {
    return if (!error?.errorMessage.isNullOrEmpty()) {
      error?.errorMessage!!
    } else if (!text.isNullOrEmpty()) {
      text!!
    } else if (textId != null) {
      context.getString(textId!!)
    } else {
      null
    }
  }
}