package com.wmart.moviedb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {
    private const val API_KEY = "1ae163a93ca5e8c11cca2f60f8f3e83a"
    private var retrofit: Retrofit?= null
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    fun getRetrofitInstance(): Retrofit?{
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}