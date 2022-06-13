package com.alancamargo.dogz.features.doglist.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alancamargo.dogz.databinding.ItemDogBinding
import com.alancamargo.dogz.features.doglist.ui.model.UiDog

class DogViewHolder(
    private val binding: ItemDogBinding,
    private val onItemClick: (UiDog) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(dog: UiDog) = with(binding) {
        imgPhoto.load(dog.imageUrl)
        txtName.text = dog.breed
        root.setOnClickListener { onItemClick(dog) }
    }

}
