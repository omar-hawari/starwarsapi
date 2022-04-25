package com.omarhawari.starwarstrivia.presentation.character_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.omarhawari.starwarstrivia.R

class CharacterDetailActivity : AppCompatActivity() {


    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        viewModel.print()

    }
}