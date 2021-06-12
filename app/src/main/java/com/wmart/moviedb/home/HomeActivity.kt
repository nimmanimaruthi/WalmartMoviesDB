package com.wmart.moviedb.home

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wmart.moviedb.BaseActivity
import com.wmart.moviedb.R
import com.wmart.moviedb.home.adapter.MoviesAdapter
import com.wmart.moviedb.home.viewmodel.HomeViewModel

class HomeActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initialiseViews()
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(this.application)
            .create(HomeViewModel::class.java)
        homeViewModel.registerForMovies().observe(this, {
            hideProgressDialog()
            if (it != null) {
                recyclerView.adapter = MoviesAdapter(it)
            }
        })
        Handler().postDelayed({
            showProgressDialog()
            homeViewModel.getMoviesFromServer()
        }, 3000)
    }

    private fun initialiseViews() {
        supportActionBar?.hide()
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}