package com.dreamworks.webteer.screens.movieDetails

import android.media.Rating
import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("setRating")
fun setRating(rating: RatingBar,value: Float){
    rating.rating = value
}
