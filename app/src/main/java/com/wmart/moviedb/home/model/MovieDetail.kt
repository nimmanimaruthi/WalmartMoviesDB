package com.wmart.moviedb.home.model

data class MovieDetail(
    val genres: List<Genres>,
    val title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String,
    val poster_path: String,
    val homepage: String
)

data class Genres(val id: Int, val name: String)