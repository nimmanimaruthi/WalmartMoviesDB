package com.wmart.moviedb.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("polupar")
    fun getMoviesList(@Query("api_key") api_key: String,
                      @Query("api_key") page: Int)
}