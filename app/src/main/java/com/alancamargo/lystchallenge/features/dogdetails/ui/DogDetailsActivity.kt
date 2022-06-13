package com.alancamargo.lystchallenge.features.dogdetails.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.lystchallenge.core.tools.args
import com.alancamargo.lystchallenge.core.tools.createIntent
import com.alancamargo.lystchallenge.core.tools.putArguments
import com.alancamargo.lystchallenge.databinding.ActivityDogDetailsBinding
import com.alancamargo.lystchallenge.features.doglist.ui.model.UiDog
import kotlinx.parcelize.Parcelize

class DogDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityDogDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by args<Args>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, args.dog.breed, Toast.LENGTH_SHORT).show()
    }

    @Parcelize
    data class Args(val dog: UiDog) : Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent(DogDetailsActivity::class).putArguments(args)
        }
    }

}
