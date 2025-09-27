package com.calyrsoft.ucbp1.features.movie.domain.usecase

import com.calyrsoft.ucbp1.features.movie.data.repository.MovieRepository
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

class ToggleLikeMovieUseCase (private val movieRepository: MovieRepository) {
    suspend fun invoke(movie: MovieModel) {
        movieRepository.toggleLike(movie)
    }
}