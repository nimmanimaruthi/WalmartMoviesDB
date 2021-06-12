package com.wmart.moviedb.api

import com.wmart.moviedb.home.model.MovieDetail
import com.wmart.moviedb.home.model.MoviesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMoviesList(@Query("api_key") api_key: String,
                      @Query("page") page: Int): Call<MoviesData>

    @GET("movie/{id}")
    fun getMoviesDetails(@Path("id") id: Int,
                         @Query("api_key") api_key: String): Call<MovieDetail>
}