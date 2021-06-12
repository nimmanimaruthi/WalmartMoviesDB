package com.wmart.moviedb.home

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wmart.moviedb.R
import com.wmart.moviedb.home.adapter.MoviesAdapter
import com.wmart.moviedb.home.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var progressDialog: ProgressDialog ?= null
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initialiseViews()
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(this.application)
            .create(HomeViewModel::class.java)
        homeViewModel.registerForMovies().observe(this, {
            dismissDialog()
            if (it != null) {
                recyclerView.adapter = MoviesAdapter(it)
            }
        })
        Handler().postDelayed({
            showProgressDialog()
            homeViewModel.getMoviesFromServer()
        }, 3000)
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(getString(R.string.pls_wait))
        progressDialog?.show()
    }

    private fun dismissDialog() {
        progressDialog?.dismiss()
    }

    private fun initialiseViews() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}