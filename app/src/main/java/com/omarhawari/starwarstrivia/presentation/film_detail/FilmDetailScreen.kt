package com.omarhawari.starwarstrivia.presentation.film_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.omarhawari.starwarstrivia.R
import com.omarhawari.starwarstrivia.common.Constants.PARAM_CHARACTER_PATH
import com.omarhawari.starwarstrivia.common.components.StarryBackground
import com.omarhawari.starwarstrivia.domain.models.Film
import com.omarhawari.starwarstrivia.presentation.character_detail.CharacterDetailActivity
import com.omarhawari.starwarstrivia.presentation.film_detail.components.KeyValueItem
import com.omarhawari.starwarstrivia.presentation.film_detail.components.ListItem
import com.omarhawari.starwarstrivia.presentation.ui.theme.starJediFont

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
        StarryBackground()
        Scaffold(
            backgroundColor = Color.Transparent,
            topBar = {
                TopAppBar(

                    backgroundColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                    title = {
                        Text(
                            text = film?.title ?: "",
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.primary,
                            fontSize = 20.sp,
                            fontFamily = starJediFont,
                            overflow = TextOverflow.Clip
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colors.primary
                            )
                        }

                    },
                    elevation = 0.dp
                )
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

                                            Spacer(
                                                modifier = Modifier
                                                    .background(MaterialTheme.colors.surface)
                                                    .height(20.dp)
                                            )

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
                KeyValueItem("Episode No", film.episodeId.toString(), modifier = Modifier)

                Text(
                    text = "Characters:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                val context = LocalContext.current

                if (characters.isEmpty())
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                else
                    LazyRow {
                        items(characters.map { it.second }) { character ->

                            ListItem(
                                character.name,
                                R.drawable.jedi_outline,
                                Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
                            ) {
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

                Text(
                    text = "Planets:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                if (planets.isEmpty())
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                else LazyRow {
                    items(planets.map { it.second }) { planet ->
                        ListItem(planet.name, R.drawable.planet)
                    }
                }

                Text(
                    text = "Star Ships:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                if (spaceShips.isEmpty())
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                else
                    LazyRow {
                        items(spaceShips.map { it.second }) { spaceShip ->
                            ListItem(
                                spaceShip.name,
                                R.drawable.spaceship,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = 20.dp)
                            )
                        }
                    }


                Text(
                    text = "Vehicles:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                if (vehicles.isEmpty())
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                else
                    LazyRow {

                        items(vehicles.map { it.second }) { vehicle ->
                            ListItem(
                                vehicle.name, R.drawable.vehicle,
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                            )

                        }
                    }
            }
        }
    }

}