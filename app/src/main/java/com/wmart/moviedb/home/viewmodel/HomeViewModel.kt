package com.wmart.moviedb.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wmart.moviedb.api.source.MoviesRemoteDataSource
import com.wmart.moviedb.home.model.Movie

class HomeViewModel(
    private val dataSource: MoviesRemoteDataSource = MoviesRemoteDataSource()
) : ViewModel() {
    private var moviesList: MutableLiveData<List<Movie>> = dataSource.register()
    private var pageIndex = 0
    fun getMoviesFromServer() {
        dataSource.getMovies(++pageIndex)
    }

    fun registerForMovies() = moviesList
}