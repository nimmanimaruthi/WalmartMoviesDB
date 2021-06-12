package com.wmart.moviedb.api.source

import androidx.lifecycle.MutableLiveData
import com.wmart.moviedb.api.ApiService
import com.wmart.moviedb.api.WebClient
import com.wmart.moviedb.home.model.Movie
import com.wmart.moviedb.home.model.MovieDetail
import com.wmart.moviedb.home.model.MoviesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRemoteDataSource : MoviesDataSource {
    private val moviesMutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    private val movieDetailMutableLiveData: MutableLiveData<MovieDetail> = MutableLiveData()

    override fun register() = moviesMutableLiveData

    override fun registerForMovieDetails() = movieDetailMutableLiveData

    override fun getMovies(page: Int) {
        WebClient.getRetrofitInstance()?.create(ApiService::class.java)
            ?.getMoviesList(API_KEY, page)?.enqueue(object : Callback<MoviesData> {
                override fun onResponse(
                    call: Call<MoviesData>,
                    response: Response<MoviesData>
                ) {
                    val moviesList = response.body()?.results
                    moviesMutableLiveData.postValue(moviesList)
                }

                override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                    moviesMutableLiveData.postValue(ArrayList())
                }
            })
    }

    override fun getMovieDetails(movieId: Int) {
        WebClient.getRetrofitInstance()
            ?.create(ApiService::class.java)
            ?.getMoviesDetails(id = movieId, API_KEY)
            ?.enqueue(object: Callback<MovieDetail> {
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    movieDetailMutableLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    movieDetailMutableLiveData.postValue(null)
                }
            })
    }

    companion object {
        private const val API_KEY = "1ae163a93ca5e8c11cca2f60f8f3e83a"
    }
}