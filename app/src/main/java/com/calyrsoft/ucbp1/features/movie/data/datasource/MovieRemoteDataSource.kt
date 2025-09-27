package com.calyrsoft.ucbp1.features.movie.data.datasource

import com.calyrsoft.ucbp1.features.movie.data.api.MovieService
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

class MovieRemoteDataSource(
    private val movieServie: MovieService,
    private val apiKey: String
) {
    suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        val response = movieServie.fetchPopularMovies(apiKey = apiKey)
        return if (response.isSuccessful) {
            val moviePage = response.body()
            if (moviePage != null) {
                return Result.success(moviePage.results.map { dto ->  MovieModel(
                    id = dto.id, // ⚠️ si dto.id es Int lo convertimos a String
                    title = dto.title,
                    overview = dto.overview,
                    posterPath = "https://image.tmdb.org/t/p/w185" + dto.pathUrl
                ) } )
            }
            Result.success(emptyList())
        } else {
            Result.failure(Exception("Error"))
        }
    }
}