package com.alancamargo.lystchallenge.features.doglist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.lystchallenge.databinding.ItemDogBinding
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog

class DogAdapter(
    private val onItemClick: (UiDog) -> Unit
) : ListAdapter<UiDog, DogViewHolder>(DogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDogBinding.inflate(inflater)
        return DogViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        holder.bindTo(dog)
    }

}
