package com.wmart.moviedb.home.model

class MoviesRecord(val page: Int,
                   val total_pages: Int,
                   val results: List<Movie>)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String,
    val poster_path: String
)