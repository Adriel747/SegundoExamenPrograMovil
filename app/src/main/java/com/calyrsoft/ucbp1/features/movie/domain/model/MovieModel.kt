package com.calyrsoft.ucbp1.features.movie.domain.model

data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val isLiked: Boolean = false
)