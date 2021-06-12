package com.wmart.moviedb.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wmart.moviedb.R
import com.wmart.moviedb.home.adapter.MoviesAdapter
import com.wmart.moviedb.home.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initialiseViews()
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(this.application)
            .create(HomeViewModel::class.java)
        homeViewModel.registerForMovies().observe(this, {
            recyclerView.adapter = MoviesAdapter()
        })
    }

    private fun initialiseViews() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}