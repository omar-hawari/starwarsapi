package com.omarhawari.starwarstrivia.presentation.character_detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omarhawari.starwarstrivia.R
import com.omarhawari.starwarstrivia.databinding.ActivityCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailViewModel by viewModels()

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}