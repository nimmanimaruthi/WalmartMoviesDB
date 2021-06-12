package com.wmart.moviedb.api

import com.wmart.moviedb.home.model.Movie
import com.wmart.moviedb.home.model.MoviesRecord
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    fun getMoviesList(@Query("api_key") api_key: String,
    @Query("page") page: Int): Call<MoviesRecord>
}