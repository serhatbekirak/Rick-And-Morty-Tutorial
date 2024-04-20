package com.sba.rickandmortytutorial.views

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.sba.rickandmortytutorial.R

class SBALoader {

  var context: Context? = null
  private var loader: Dialog? = null

  fun showLoader() {
    loader = (loader ?: createLoader())
    if (loader?.isShowing == false) {
      loader?.show()
    }
  }

  fun hideLoader() {
    try {
      if (loader?.isShowing == true) {
        loader?.dismiss()
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  fun clearLoader() {
    hideLoader()
    loader = null
    context = null
  }

  private fun createLoader(): Dialog? {
    try {
      val dialog = Dialog(context!!)
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
      dialog.window?.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
      )
      dialog.setCancelable(false)

      dialog.setContentView(R.layout.custom_dialog_loading)
      dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      return dialog

    } catch (e: Exception) {
      e.printStackTrace()
      return null
    }
  }
}