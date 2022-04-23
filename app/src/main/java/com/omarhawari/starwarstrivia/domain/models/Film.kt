package com.omarhawari.starwarstrivia.domain.models

data class Film(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episodeId: Int,
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val vehicles: List<String>
) {
    override fun toString(): String {
        return "Film(characters=$characters, created='$created', director='$director', edited='$edited', episodeId=$episodeId, openingCrawl='$openingCrawl', planets=$planets, producer='$producer', releaseDate='$releaseDate', species=$species, starships=$starships, title='$title', vehicles=$vehicles)"
    }
}
