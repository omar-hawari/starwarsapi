package com.omarhawari.starwarstrivia.presentation.films

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.omarhawari.starwarstrivia.presentation.films.components.FilmItem


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FilmList(navController: NavController, viewModel: FilmsViewModel = hiltViewModel()) {

    val state = viewModel.state


    val swipeRefreshState = rememberSwipeRefreshState(state.value.isLoading)
    Scaffold(
        topBar = {
            TopAppBar {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "STAR WARS TRIVIA",
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

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.value.films) { film ->
                        FilmItem(film = film, navController = navController)
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