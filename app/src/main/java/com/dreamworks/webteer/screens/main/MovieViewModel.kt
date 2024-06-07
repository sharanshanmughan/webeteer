package com.dreamworks.webteer.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamworks.webteer.model.HomeData
import com.dreamworks.webteer.model.Movieslist
import com.dreamworks.webteer.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) :ViewModel() {
    val genreList = MutableLiveData<MutableList<String>>(arrayListOf())
    private val homeDataLive = MutableLiveData<List<HomeData>>(arrayListOf())
    val showList = MutableLiveData("All")
    val movieList = MutableLiveData<List<Movieslist>>(arrayListOf())
    val movieData = MutableLiveData<Movieslist>(null)
    private val firstLoad = MutableLiveData(true)
    val loader = MutableLiveData(8)


     fun getAccessMovieRepo(){
         loader.value = 0
         viewModelScope.launch {
             val response =  movieRepo.getMovieList()
             if (response.isSuccessful){
                 loader.value=8
                 homeDataLive.value = response.body()!!.homeData

                 if (firstLoad.value!!){
                     firstLoad.value =false
                     movieList.value = getMovies()
                 }


                 val homeData = homeDataLive.value
                 val genreData:MutableList<String> = arrayListOf()
                 for (i in homeData!!.indices){
                     genreData.add(homeData[i].genre)
                 }
                 genreData.add("All")
                 genreList.value = genreData




             }else{
                 loader.value =8
             }
         }

    }



    fun onGenreSelected(genre:String):String{

        val data = getMoviesByGenre(genre)
        movieList.value = data.toMutableList()
        showList.value = genre
        return genre
    }



    private fun getMoviesByGenre(genre: String):List<Movieslist> {
        return if(genre!="All") {homeDataLive.value!!.flatMap { it.movieslist }
            .filter { it.genre.contains(genre) }
        }else{
            getMovies()
        }


    }

    private fun getMovies():List<Movieslist> {
        return homeDataLive.value!!.flatMap { it.movieslist }

    }


}