package com.dreamworks.webteer.repo

import com.dreamworks.webteer.model.MovieData
import com.dreamworks.webteer.service.MovieService
import retrofit2.Response
import javax.inject.Inject


class MovieRepo @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getMovieList():Response<MovieData>{
        return movieService.getData()
    }
}