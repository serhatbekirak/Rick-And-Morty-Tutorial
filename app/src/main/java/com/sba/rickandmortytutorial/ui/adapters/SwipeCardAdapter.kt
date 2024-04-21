package com.sba.rickandmortytutorial.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sba.rickandmortytutorial.data.model.Character
import com.sba.rickandmortytutorial.databinding.ItemSwipeCardBinding
import com.sba.rickandmortytutorial.utils.extensions.setImage

class SwipeCardAdapter : RecyclerView.Adapter<SwipeCardAdapter.ViewHolder>() {
  private var items = ArrayList<Character>()

  fun refreshContents(contents: List<Character>) {
    items.addAll(contents)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount(): Int {
    return items.size
  }


  class ViewHolder(private var binding: ItemSwipeCardBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Character) {
      binding.apply {
        ivImage.setImage(item.image)
        tvName.text = item.name
      }
    }

    companion object {
      fun from(parent: ViewGroup): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSwipeCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
      }
    }
  }
}