package com.omarhawari.starwarstrivia.presentation.films.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omarhawari.starwarstrivia.R
import com.omarhawari.starwarstrivia.domain.models.Film


val mapEpisodeIdToImage = mapOf(
    Pair(1, R.drawable.star_wars_4),
    Pair(2, R.drawable.star_wars_5),
    Pair(3, R.drawable.star_wars_6),
    Pair(4, R.drawable.star_wars_1),
    Pair(5, R.drawable.star_wars_2),
    Pair(6, R.drawable.star_wars_3)
)
@Composable
fun FilmItem(
    film: Film, onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick() },
        backgroundColor = MaterialTheme.colors.secondary.copy(alpha = 0.4f),
        elevation = 0.dp
    ) {
        Row(modifier = Modifier.height(150.dp)) {
            Image(
                painter = painterResource(mapEpisodeIdToImage[film.episodeId]!!),
                contentDescription = "MoviePoster",
                modifier = Modifier
                    .fillMaxHeight(),
//                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = film.title,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp),
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = film.releaseDate.split("-")[0],
                    modifier = Modifier.padding(start = 12.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Episode No. ${film.episodeId}",
                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

    }
}