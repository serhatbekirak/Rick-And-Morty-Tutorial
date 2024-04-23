package com.sba.rickandmortytutorial.data.model

import android.graphics.Color
import com.sba.rickandmortytutorial.R

data class Character(
  var id: Int? = null,
  var name: String? = null,
  var status: String? = null,
  var species: String? = null,
  var type: String? = null,
  var gender: String? = null,
  var origin: Origin? = Origin(),
  var location: Location? = Location(),
  var image: String? = null,
  var episode: ArrayList<String> = arrayListOf(),
  var url: String? = null,
  var created: String? = null
) {
  val statusColor: Int
    get() {
      return when (this.status) {
        "Alive" -> R.color.green
        "Dead" -> R.color.red
        "unknown" -> R.color.yellow
        else -> R.color.yellow
      }
    }
}