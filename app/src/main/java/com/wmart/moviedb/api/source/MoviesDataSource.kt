package com.wmart.moviedb.api.source

import androidx.lifecycle.MutableLiveData
import com.wmart.moviedb.home.model.Movie
import com.wmart.moviedb.home.model.MovieDetail

interface MoviesDataSource {
    fun register() : MutableLiveData<List<Movie>>
    fun registerForMovieDetails() : MutableLiveData<MovieDetail>
    fun getMovies(page: Int)
    fun getMovieDetails(movieId: Int)
}