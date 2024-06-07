package com.dreamworks.webteer.service

import com.dreamworks.webteer.model.MovieData
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("dummy.json")
    suspend fun getData():Response<MovieData>
}