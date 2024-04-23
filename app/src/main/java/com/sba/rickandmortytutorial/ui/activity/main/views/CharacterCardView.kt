package com.sba.rickandmortytutorial.ui.activity.main.views

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.sba.rickandmortytutorial.data.callback.ISwipeCardCallback

@SuppressLint("ClickableViewAccessibility")
class CharacterCardView(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

  private lateinit var swipeActionListener: ISwipeCardCallback
  private lateinit var item: ItemSwipeCardView

  fun setSwipeActionListener(item: ItemSwipeCardView, swipeActionListener: ISwipeCardCallback) {
    this.swipeActionListener = swipeActionListener
    this.item = item
  }

  init {
    var startX = 0
    var startY = 0
    var xCord: Int
    var yCord: Int
    val margin = 80f
    var toRemove = false

    val displayMetrics = DisplayMetrics()
    (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    val windowWidth = displayMetrics.widthPixels
    val windowHeight = displayMetrics.heightPixels
    val screenCenterHorizontal = windowWidth / 2
    val screenCenterVertical = windowHeight / 2

    setOnTouchListener { _, motionEvent ->
      xCord = motionEvent.rawX.toInt()
      yCord = motionEvent.rawY.toInt()

      when (motionEvent.action) {
        MotionEvent.ACTION_DOWN -> {
          startX = motionEvent.x.toInt()
          startY = motionEvent.y.toInt()
        }

        MotionEvent.ACTION_MOVE -> {
          xCord = motionEvent.rawX.toInt()
          yCord = motionEvent.rawY.toInt()

          x = (xCord - startX).toFloat()
          y = (yCord - startY).toFloat()

          toRemove = if (xCord >= screenCenterHorizontal) {
            rotation = 10f

            if (xCord > screenCenterHorizontal + screenCenterHorizontal / 2) {
              xCord > windowWidth - screenCenterHorizontal / 3
            } else {
              false
            }
          } else {
            rotation = -10f

            if (xCord < screenCenterHorizontal / 2) {
              xCord < screenCenterHorizontal / 3
            } else {
              false
            }
          }

          if (!toRemove) {
            toRemove = if (yCord >= screenCenterVertical) {
              if (yCord > screenCenterVertical + screenCenterVertical / 2) {
                yCord > windowWidth - screenCenterVertical / 4
              } else {
                false
              }
            } else {
              if (yCord < screenCenterVertical / 2) {
                yCord < screenCenterVertical / 4
              } else {
                false
              }
            }
          }
        }

        MotionEvent.ACTION_UP -> {
          xCord = motionEvent.rawX.toInt()
          yCord = motionEvent.rawY.toInt()

          if (toRemove) {
            if (rotation < 0) swipeActionListener.onSwipeLeftOut(item)
            else swipeActionListener.onSwipeRightOut(item)
          } else {
            x = margin
            y = margin
            rotation = 0f
          }
        }
      }
      true
    }
  }
}