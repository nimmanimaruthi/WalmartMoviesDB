package com.wmart.moviedb.api.source

import androidx.lifecycle.MutableLiveData
import com.wmart.moviedb.home.model.Movie

interface MoviesDataSource {
    fun register() : MutableLiveData<List<Movie>>
    fun getMovies(page: Int)
}