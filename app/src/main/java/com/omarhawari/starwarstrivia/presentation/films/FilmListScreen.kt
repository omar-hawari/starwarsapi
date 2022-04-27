package com.omarhawari.starwarstrivia.presentation.films

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.omarhawari.starwarstrivia.presentation.Screen
import com.omarhawari.starwarstrivia.presentation.films.components.FilmItem
import com.omarhawari.starwarstrivia.presentation.ui.theme.starJediFont


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FilmList(navController: NavController, viewModel: FilmsViewModel = hiltViewModel()) {

    val state = viewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(state.value.isLoading)
    Scaffold(
        topBar = {
            TopAppBar {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "STAR WARS",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontFamily = starJediFont
                        )
                    }

                    IconButton(onClick = {
                        navController.navigate(
                            Screen.SettingsScreen.route
                        )
                    }, modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }

                }
            }
        }
    ) { _ ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.refresh()
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.value.films) { film ->
                        FilmItem(film = film, navController = navController) {
                            navController.navigate(
                                Screen.FilmDetailScreen.route + "/${
                                    state.value.films.indexOf(
                                        film
                                    ) + 1
                                }"
                            )
                        }
                    }
                }
                if (state.value.isLoading) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }


}