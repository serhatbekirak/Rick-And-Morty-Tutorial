package com.sba.rickandmortytutorial.ui.activity.main.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.sba.rickandmortytutorial.R
import com.sba.rickandmortytutorial.data.callback.ISwipeCardCallback
import com.sba.rickandmortytutorial.data.model.Character
import com.sba.rickandmortytutorial.databinding.ItemCharacterCardBinding
import com.sba.rickandmortytutorial.utils.extensions.setImage

class ItemSwipeCardView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
  private val binding: ItemCharacterCardBinding

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = ItemCharacterCardBinding.inflate(inflater, this, true)
  }

  @SuppressLint("SetTextI18n")
  fun configureUI(item: Character, callBack: ISwipeCardCallback) {
    binding.apply {
      tvName.text = item.name
      ivImage.setImage(item.image)
      tvStatus.text = "${item.status} - ${item.species}"
      tvLastLocation.text = context.getString(R.string.last_location) + ":  " + (item.location?.name ?: "-")
      ivStatus.setColorFilter(ContextCompat.getColor(context, item.statusColor), PorterDuff.Mode.SRC_ATOP)
      swCardView.setSwipeActionListener(this@ItemSwipeCardView, callBack)
      ivLike.setOnClickListener { callBack.onSwipeRightOut(this@ItemSwipeCardView) }
      ivNope.setOnClickListener { callBack.onSwipeLeftOut(this@ItemSwipeCardView) }

      val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
      layoutParams.setMargins(80, 80, 80, 80)
      binding.root.layoutParams = layoutParams
    }
  }
}