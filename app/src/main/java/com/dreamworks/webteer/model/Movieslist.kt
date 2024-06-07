package com.dreamworks.webteer.model

data class Movieslist(
    val desc: String,
    val genre: List<String>,
    val id: String,
    val posterurl: String,
    val rating: String,
    val release: String,
    val title: String
)