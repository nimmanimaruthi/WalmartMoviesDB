package com.wmart.moviedb.api

import com.wmart.moviedb.home.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("polupar")
    fun getMoviesList(@Query("api_key") api_key: String,
    @Query("api_key") page: Int): Call<List<Movie>>
}