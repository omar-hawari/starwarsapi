package com.omarhawari.starwarstrivia.presentation.film_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
import com.omarhawari.starwarstrivia.domain.models.Film
import com.omarhawari.starwarstrivia.presentation.character_detail.CharacterDetailActivity
import com.omarhawari.starwarstrivia.presentation.film_detail.components.*
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

                                val configuration = LocalConfiguration.current
                                when (configuration.orientation) {
                                    Configuration.ORIENTATION_LANDSCAPE -> {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                        ) {

                                            OpeningCrawl(
                                                film = film!!,
                                                modifier = Modifier.weight(1f)
                                            )

                                            FilmDetails(
                                                film = film,
                                                modifier = Modifier.weight(1f),
                                                state = state
                                            )

                                        }

                                    }
                                    else -> {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                        ) {

                                            OpeningCrawl(
                                                film = film!!,
                                                modifier = Modifier.weight(1f)
                                            )

                                            Spacer(modifier = Modifier.height(20.dp))

                                            FilmDetails(
                                                film = film,
                                                modifier = Modifier.weight(2f),
                                                state = state
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

}

@Composable
fun OpeningCrawl(film: Film, modifier: Modifier) {
    Box(modifier = modifier) {
        LazyColumn {
            item {
                Text(
                    "0pening Crawl",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    text = film.openingCrawl,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.background.copy(alpha = 0.5f),
                            Color.Transparent
                        )
                    )
                )
                .align(Alignment.TopCenter)
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.background.copy(alpha = 0.5f)
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun FilmDetails(film: Film, modifier: Modifier, state: MutableState<FilmDetailState>) {

    val characters = state.value.characters
    val planets = state.value.planets
    val spaceShips = state.value.spaceShips
    val vehicles = state.value.vehicles


    LazyColumn(modifier = modifier) {
        item {
            Column {

                KeyValueItem("Director", film.director, modifier = Modifier)
                KeyValueItem("Producer", film.producer, modifier = Modifier)
                KeyValueItem("Release Date", film.releaseDate, modifier = Modifier)

                Text(text = "Characters:")


                val context = LocalContext.current
                LazyRow {

                    if (characters.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    } else {
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
                }

                Text(text = "Planets:")
                LazyRow {
                    if (planets.isEmpty())
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    else
                        items(planets.map { it.second }) { planet ->
                            PlanetItem(planet)
                        }
                }


                Text(text = "Star Ships:")
                LazyRow {
                    if (spaceShips.isEmpty())
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    else
                        items(spaceShips.map { it.second }) { spaceShip ->
                            SpaceShipItem(spaceShip)
                        }
                }

                Text(text = "Vehicles:")
                LazyRow {
                    if (vehicles.isEmpty())
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    else
                        items(vehicles.map { it.second }) { vehicle ->
                            VehicleItem(vehicle)
                        }
                }
            }
        }
    }

}