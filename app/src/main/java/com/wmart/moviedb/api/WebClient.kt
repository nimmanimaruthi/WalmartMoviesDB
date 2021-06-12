package com.wmart.moviedb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        private var retrofit: Retrofit? = null
        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }