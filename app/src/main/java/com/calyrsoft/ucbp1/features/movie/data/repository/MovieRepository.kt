package com.calyrsoft.ucbp1.features.movie.data.repository

import IMovieDao
import com.calyrsoft.ucbp1.features.movie.data.datasource.MovieRemoteDataSource
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.repository.IMoviesRepository
import toEntity

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: IMovieDao
): IMoviesRepository {

    override suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        return try {
            val apiResult = movieRemoteDataSource.fetchPopularMovies()
            if (apiResult.isSuccess) {
                val apiMovies = apiResult.getOrNull()!!

                val likedMoviesIds = movieDao.getMoviesSortedOnce().map { it.id }.toSet()

                val mergedMovies = apiMovies.map { movie ->
                    movie.copy(isLiked = likedMoviesIds.contains(movie.id))
                }

                // Ordenar primero los liked
                Result.success(mergedMovies.sortedByDescending { it.isLiked })
            } else {
                Result.failure(apiResult.exceptionOrNull()!!)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun toggleLike(movie: MovieModel) {
        val updated = movie.copy(isLiked = !movie.isLiked)
        movieDao.insertMovie(updated.toEntity()) // <-- ahora Room sabe el tipo
    }
}
