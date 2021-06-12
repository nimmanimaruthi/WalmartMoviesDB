package com.wmart.moviedb.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wmart.moviedb.R
import com.wmart.moviedb.api.WebClient
import com.wmart.moviedb.details.MovieDetailActivity
import com.wmart.moviedb.home.model.Movie

class MoviesAdapter(private var movies: List<Movie> = ArrayList()) :
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    fun setData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val movie = movies.get(position)
        holder.title.text = movie.title
        holder.rating.text = movie.popularity.toString()
        Picasso.with(holder.bannerImage.context)
            .load(WebClient.IMAGE_BASE_URL + movie.poster_path)
            .into(holder.bannerImage)
        holder.container.setOnClickListener {
            val intent = Intent(holder.container.context, MovieDetailActivity::class.java)
            intent.apply {
                putExtra(MovieDetailActivity.KEY_ID, movie.id)
                putExtra(MovieDetailActivity.KEY_IMAGE, movie.id)
                putExtra(MovieDetailActivity.KEY_TITLE, movie.title)
                putExtra(MovieDetailActivity.KEY_OVERVIEW, movie.overview)
                putExtra(MovieDetailActivity.KEY_RELEASE, movie.release_date)
            }
            holder.container.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.findViewById<ConstraintLayout>(R.id.container)
        var title = itemView.findViewById<TextView>(R.id.title)
        var rating = itemView.findViewById<TextView>(R.id.rating)
        var bannerImage = itemView.findViewById<ImageView>(R.id.banner_image)
    }
}