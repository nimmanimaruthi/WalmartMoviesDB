package com.wmart.moviedb.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wmart.moviedb.api.source.MoviesRemoteDataSource
import com.wmart.moviedb.home.model.MovieDetail

class MovieDetailsViewModel(
    private val dataSource: MoviesRemoteDataSource = MoviesRemoteDataSource()
) : ViewModel() {

    private val movieDetailsLiveData: MutableLiveData<MovieDetail> =
        dataSource.registerForMovieDetails()

    fun registerForMovies() = movieDetailsLiveData

    fun getMovieDetails(movieId: Int) {
        dataSource.getMovieDetails(movieId)
    }
}