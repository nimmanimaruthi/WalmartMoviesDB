package com.wmart.moviedb.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wmart.moviedb.home.model.Movie

class HomeViewModel: ViewModel() {
    private lateinit var moviesList: MutableLiveData<Movie>

    fun getMoviesFromServer() {

    }

    fun registerForMovies(): LiveData<Movie> {
        return moviesList
    }
}