package com.wmart.moviedb.api.source

import androidx.lifecycle.MutableLiveData
import com.wmart.moviedb.api.ApiService
import com.wmart.moviedb.api.WebClient
import com.wmart.moviedb.home.model.Movie
import com.wmart.moviedb.home.model.MoviesRecord
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRemoteDataSource : MoviesDataSource {
    private val moviesMutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    override fun register() = moviesMutableLiveData

    override fun getMovies(page: Int) {
        WebClient.getRetrofitInstance()?.create(ApiService::class.java)
            ?.getMoviesList(API_KEY, page)?.enqueue(object : Callback<MoviesRecord> {
                override fun onResponse(
                    call: Call<MoviesRecord>,
                    response: Response<MoviesRecord>
                ) {
                    val moviesList = response.body()?.results
                    moviesMutableLiveData.postValue(moviesList)
                }

                override fun onFailure(call: Call<MoviesRecord>, t: Throwable) {
                    moviesMutableLiveData.postValue(ArrayList())
                }
            })
    }

    companion object {
        private const val API_KEY = "1ae163a93ca5e8c11cca2f60f8f3e83a"
    }
}