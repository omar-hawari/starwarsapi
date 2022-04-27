package com.omarhawari.starwarstrivia.presentation.film_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.omarhawari.starwarstrivia.domain.models.Planet
import com.omarhawari.starwarstrivia.domain.models.SpaceShip

@Composable
fun SpaceShipItem(spaceShip: SpaceShip) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        modifier = Modifier
            .padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                Icons.Default.AirplanemodeActive,
                contentDescription = "SpaceShip",
                modifier = Modifier.size(30.dp)
            )
            Text(text = spaceShip.name)
        }
    }
}