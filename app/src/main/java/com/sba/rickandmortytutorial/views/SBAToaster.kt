package com.sba.rickandmortytutorial.views

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sba.rickandmortytutorial.R
import com.sba.rickandmortytutorial.data.enums.ToastType
import com.sba.rickandmortytutorial.utils.SingletonHolder

class SBAToaster private constructor(val context: Context) {

  private lateinit var toast: Toast

  fun showToast(message: String, type: ToastType) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val customLayout = inflater.inflate(R.layout.custom_toast, null)

    val tvMessage = customLayout.findViewById<TextView>(R.id.tv_toast_message)
    tvMessage.text = message

    val ivStart = customLayout.findViewById<ImageView>(R.id.iv_start_icon)
    ivStart.setImageDrawable(ContextCompat.getDrawable(context, type.style))

    toast = Toast(context)
    toast.setGravity(Gravity.TOP, 0, 20)
    toast.duration = Toast.LENGTH_LONG
    toast.view = customLayout
    toast.show()
  }

  fun hideToast() {
    if (::toast.isInitialized) {
      toast.cancel()
    }
  }

  companion object : SingletonHolder<SBAToaster, Context>(::SBAToaster)
}