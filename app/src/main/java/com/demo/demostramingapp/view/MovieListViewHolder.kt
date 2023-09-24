package com.demo.demostramingapp.view

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.demo.demostramingapp.model.PosterImageValue
import com.demo.demostramingapp.R
import com.demo.demostramingapp.model.Content
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

/** [MovieListViewHolder] is used set the Recyclerview Items. */
class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val movieTitle: MaterialTextView = itemView.findViewById(R.id.movieTitle)
    private val movieImage: ShapeableImageView = itemView.findViewById(R.id.movieImage)

    /** Bind the list data with recyclerview items. */
    fun bind(movieListData: Content, searchQuery: String) {
        val drawable = movieListData.`poster-image`
        movieImage.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                getDrawable(drawable)
            )
        )
        // Highlight the search term in the text view
        if (!searchQuery.isNullOrBlank()) {
            val spannable = SpannableString(movieListData.name)
            val startIndex = movieListData.name.indexOf(searchQuery, ignoreCase = true)
            if (startIndex != -1) {
                val endIndex = startIndex + searchQuery.length
                spannable.setSpan(
                    BackgroundColorSpan(Color.GREEN), // Highlight color
                    startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            movieTitle.text = spannable
        } else {
            movieTitle.text = movieListData.name
        }
    }

    /** Method used to get the drawable for particular movie. */
    private fun getDrawable(drawable: String): Int {
        return when (drawable) {
            PosterImageValue.POSTER1.poster -> R.drawable.poster1
            PosterImageValue.POSTER2.poster -> R.drawable.poster2
            PosterImageValue.POSTER3.poster -> R.drawable.poster3
            PosterImageValue.POSTER4.poster -> R.drawable.poster4
            PosterImageValue.POSTER5.poster -> R.drawable.poster5
            PosterImageValue.POSTER6.poster -> R.drawable.poster6
            PosterImageValue.POSTER7.poster -> R.drawable.poster7
            PosterImageValue.POSTER8.poster -> R.drawable.poster8
            PosterImageValue.POSTER9.poster -> R.drawable.poster9
            else -> R.drawable.placeholder_for_missing_posters
        }
    }
}