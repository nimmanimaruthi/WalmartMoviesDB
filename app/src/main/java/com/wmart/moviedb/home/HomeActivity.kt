package com.wmart.moviedb.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.wmart.moviedb.BaseActivity
import com.wmart.moviedb.R
import com.wmart.moviedb.home.adapter.MoviesAdapter
import com.wmart.moviedb.home.model.Movie
import com.wmart.moviedb.home.viewmodel.HomeViewModel

class HomeActivity : BaseActivity() {
    private var isLoading: Boolean = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var movies: List<Movie>
    private var moviesAdapter: MoviesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initialiseViews()
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(this.application)
            .create(HomeViewModel::class.java)
        homeViewModel.registerForMovies().observe(this, {
            progressBar.visibility = View.GONE
            hideProgressDialog()
            if (it != null && it.size > 0) {
                isLoading = false
                this.movies = it
                if (moviesAdapter == null) {
                    moviesAdapter = MoviesAdapter(it)
                    recyclerView.adapter = MoviesAdapter(it)
                }
                moviesAdapter?.setData(it)
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content), getString(R.string.no_internet),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
        Handler().postDelayed({
            showProgressDialog()
            homeViewModel.getMoviesFromServer()
        }, 1000)
    }

    private fun initialiseViews() {
        supportActionBar?.hide()
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == movies.size - 1) {
                        loadMovies()
                        isLoading = true
                    }
                }
            }
        })
    }

    fun loadMovies() {
        progressBar.visibility = View.VISIBLE
        homeViewModel.getMoviesFromServer()
    }
}