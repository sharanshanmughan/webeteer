package com.dreamworks.webteer.model

data class HomeData(
    val genre: String,
    val id: String,
    val movieslist: List<Movieslist>,
    val type: Int
)