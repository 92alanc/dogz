package com.alancamargo.lystchallenge.features.doglist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog

class DogDiffCallback : DiffUtil.ItemCallback<UiDog>() {

    override fun areItemsTheSame(oldItem: UiDog, newItem: UiDog): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UiDog, newItem: UiDog): Boolean {
        return oldItem == newItem
    }

}
