package com.omarhawari.starwarstrivia.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omarhawari.starwarstrivia.common.Constants
import com.omarhawari.starwarstrivia.common.components.StarryBackground
import com.omarhawari.starwarstrivia.presentation.film_detail.FilmDetailScreen
import com.omarhawari.starwarstrivia.presentation.films.FilmList
import com.omarhawari.starwarstrivia.presentation.ui.theme.StarWarsTriviaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTriviaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    StarryBackground()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.FilmsScreen.route
                    ) {
                        composable(route = Screen.FilmsScreen.route) {
                            FilmList(navController = navController)
                        }
                        composable(
                            route = Screen.FilmDetailScreen.route + "/{${Constants.PARAM_FILM_INDEX}}"
                        ) {
                            FilmDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
