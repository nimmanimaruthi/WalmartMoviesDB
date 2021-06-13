package com.wmart.moviedb.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.wmart.moviedb.BaseActivity
import com.wmart.moviedb.R
import com.wmart.moviedb.api.WebClient
import com.wmart.moviedb.home.model.MovieDetail

class MovieDetailActivity : BaseActivity() {
    private lateinit var movieViewModel: MovieDetailsViewModel
    private lateinit var titleView: TextView
    private lateinit var genresView: TextView
    private lateinit var description: TextView
    private lateinit var releaseView: TextView
    private lateinit var homepageView: TextView
    private lateinit var bannerImage: ImageView
    private lateinit var movieDetails: MovieDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initialiseViews()
        movieViewModel = ViewModelProvider.AndroidViewModelFactory(this.application)
            .create(MovieDetailsViewModel::class.java)
        movieViewModel.registerForMovies().observe(this, {
            hideProgressDialog()
            if (it != null) {
                movieDetails = it
                it.apply {
                    titleView.text = title
                    description.text = overview
                    releaseView.text = getString(R.string.released_on) + release_date
                    genresView.text = genres.map { model -> model.name }.toString()
                    if (homepage != null) {
                        homepageView.text = homepage
                    }
                    Picasso.with(this@MovieDetailActivity)
                        .load(WebClient.IMAGE_BASE_URL + poster_path)
                        .into(bannerImage)
                }
            } else {
                intent.apply {
                    titleView.text = getStringExtra(KEY_TITLE)
                    description.text = getStringExtra(KEY_OVERVIEW)
                    releaseView.text = getString(R.string.released_on) + getStringExtra(KEY_RELEASE)
                    Picasso.with(this@MovieDetailActivity)
                        .load(WebClient.IMAGE_BASE_URL + getStringExtra(KEY_IMAGE))
                        .into(bannerImage)
                }
            }
        })
        showProgressDialog()
        movieViewModel.getMovieDetails(intent.getIntExtra(KEY_ID, 0))
    }

    private fun initialiseViews() {
        supportActionBar?.hide()
        titleView = findViewById(R.id.title)
        description = findViewById(R.id.decription)
        genresView = findViewById(R.id.genres)
        releaseView = findViewById(R.id.release)
        homepageView = findViewById(R.id.homepage_link)
        bannerImage = findViewById(R.id.banner_image)
        bannerImage.setOnClickListener {
            if (!movieDetails.homepage?.isEmpty()!!) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(movieDetails.homepage)))
            }
        }
    }

    companion object {
        const val KEY_ID: String = "id"
        const val KEY_IMAGE: String = "image_url"
        const val KEY_TITLE: String = "title"
        const val KEY_OVERVIEW: String = "overview"
        const val KEY_RELEASE: String = "release"
    }
}