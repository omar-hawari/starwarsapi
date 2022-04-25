package com.omarhawari.starwarstrivia.presentation.film_detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.omarhawari.starwarstrivia.common.Constants.PARAM_CHARACTER_PATH
import com.omarhawari.starwarstrivia.presentation.character_detail.CharacterDetailActivity
import com.omarhawari.starwarstrivia.presentation.film_detail.components.CharacterItem
import com.omarhawari.starwarstrivia.presentation.film_detail.components.PlanetItem
import com.omarhawari.starwarstrivia.presentation.film_detail.components.SpaceShipItem
import com.omarhawari.starwarstrivia.presentation.film_detail.components.VehicleItem
import com.omarhawari.starwarstrivia.presentation.ui.theme.starJediFont


val offsets = arrayListOf<Pair<Dp, Dp>>()

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FilmDetailScreen(
    navController: NavController,
    viewModel: FilmDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state

    val film = state.value.film

    val characters = state.value.characters
    val planets = state.value.planets
    val spaceShips = state.value.spaceShips
    val vehicles = state.value.vehicles

    val swipeRefreshState = rememberSwipeRefreshState(state.value.isLoading)

    Box {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            repeat(2000) {
                if (offsets.size < it + 1)
                    offsets.add(
                        Pair(
                            (0..1000).random().dp,
                            (0..1000).random().dp
                        )
                    )
                Box(
                    modifier = Modifier
                        .offset(
                            x = offsets[it].first,
                            y = offsets[it].second
                        )
                        .height(1.dp)
                        .width(1.dp)
                        .background(if (MaterialTheme.colors.isLight) Color.Black else Color.White)
                        .alpha(0.1f)
                )
            }
        }
        Scaffold(
            backgroundColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                    title = {
                        Text(
                            text = film?.title ?: "",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontFamily = starJediFont
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }

                    })
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
                        .background(Color.Transparent)
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
                            Box {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(rememberScrollState())
                                ) {

                                    Text(text = film!!.openingCrawl, textAlign = TextAlign.Center)

                                    Text(text = "Director: ${film.director}")
                                    Text(text = "Producer: ${film.producer}")
                                    Text(text = "Release Date: ${film.releaseDate}")

                                    Text(text = "Characters:")


                                    val context = LocalContext.current
                                    LazyRow {
                                        items(characters.map { it.second }) { character ->

                                            CharacterItem(character) {
                                                context.startActivity(
                                                    Intent(
                                                        context,
                                                        CharacterDetailActivity::class.java
                                                    ).apply {
                                                        putExtra(
                                                            PARAM_CHARACTER_PATH,
                                                            character.url
                                                        )
                                                    }
                                                )
                                            }
                                        }
                                    }

                                    Text(text = "Planets:")
                                    LazyRow {
                                        items(planets.map { it.second }) { planet ->
                                            PlanetItem(planet) {

                                            }
                                        }
                                    }


                                    Text(text = "Star Ships:")
                                    LazyRow {
                                        items(spaceShips.map { it.second }) { spaceShip ->
                                            SpaceShipItem(spaceShip) {

                                            }
                                        }
                                    }

                                    Text(text = "Vehicles:")
                                    LazyRow {
                                        items(vehicles.map { it.second }) { vehicle ->
                                            VehicleItem(vehicle) {

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

    }

}