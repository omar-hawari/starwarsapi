package com.omarhawari.starwarstrivia.presentation.film_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.omarhawari.starwarstrivia.presentation.Screen
import com.omarhawari.starwarstrivia.presentation.films.components.FilmItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FilmDetailScreen(
    navController: NavController,
    viewModel: FilmDetailViewModel = hiltViewModel()
) {


    val state = viewModel.state

    val film = state.value.film

    val swipeRefreshState = rememberSwipeRefreshState(state.value.isLoading)

    Scaffold(
        topBar = {
            TopAppBar {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = film?.title ?: "",
                        color = Color.White,
                        fontSize = 24.sp,
                    )
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

                when {
                    state.value.isLoading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    state.value.error.isNotBlank() -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = state.value.error,
                                color = MaterialTheme.colors.onError
                            )
                        }
                    }
                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item {
                                Text(text = "Director: ${film!!.director}")
                            }
                            item {
                                Text(text = "Producer: ${film!!.producer}")
                            }
                            item {
                                Text(text = "Release Date: ${film!!.releaseDate}")
                            }
//                            item() {
//                                Text(
//                                    text = film!!.openingCrawl, modifier = Modifier.height(40.dp),
//                                    marqueeRepeatLimit = RepeateFor
//                                )
//                            }
                        }
                    }
                }

            }

        }
    }
}