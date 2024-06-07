package com.dreamworks.webteer.screens.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat

class MovieDetailsViewModel:ViewModel() {
    val moviePoster = MutableLiveData("")
    val movieName = MutableLiveData("")
    val movieGenre = MutableLiveData("")
    val movieYear = MutableLiveData("")
    val movieRating = MutableLiveData(0f)
    val movieDescription = MutableLiveData("")

    fun formatDate(value: String){
        val formatter = SimpleDateFormat("YYYY")
        val date = formatter.parse(value)

        movieYear.value = formatter.format(date!!)


    }

    fun getMovieGenre(genreList:List<String>){
        var genre = ""
        for (i in genreList){
            genre= "$genre$i / "

        }
        movieGenre.value = genre.substring(0, genre.length - 2)
    }
}