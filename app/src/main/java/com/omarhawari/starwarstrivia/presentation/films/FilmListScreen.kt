package com.omarhawari.starwarstrivia.presentation.films

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.omarhawari.starwarstrivia.common.components.StarryBackground
import com.omarhawari.starwarstrivia.presentation.Screen
import com.omarhawari.starwarstrivia.presentation.films.components.FilmItem


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FilmList(navController: NavController, viewModel: FilmsViewModel = hiltViewModel()) {

    val state = viewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(state.value.isLoading)

    Box {
        StarryBackground()
        Scaffold(
            backgroundColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.2f),
                    modifier = Modifier.height(100.dp),
                    elevation = 0.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "STAR\nWARS",
                                style = MaterialTheme.typography.h1
                            )
                        }
                    }
                }
            }
        ) {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.refresh()
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {

                    when {
                        state.value.isLoading -> {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        state.value.error.isNotBlank() -> {
                            Column(
                                modifier = Modifier
                                    .clickable {
                                        viewModel.refresh()
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    Icons.Default.Refresh,
                                    "Error",
                                    modifier = Modifier.size(50.dp),
                                    tint = MaterialTheme.colors.primaryVariant
                                )
                                Text(
                                    text = state.value.error + "\nClick to refresh.",
                                    color = MaterialTheme.colors.primaryVariant,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        else -> {
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(state.value.films) { film ->
                                    FilmItem(film = film) {
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

                        }

                    }
                }

            }
        }

    }


}