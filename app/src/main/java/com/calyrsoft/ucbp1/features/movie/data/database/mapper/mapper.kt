import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

fun MovieModel.toEntity(): MovieEntity {
    val entity = MovieEntity()
    entity.id = this.id
    entity.title = this.title
    entity.overview = this.overview
    entity.posterPath = this.posterPath
    entity.isLiked = this.isLiked
    return entity
}

fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        id = this.id,
        title = this.title ?: "",
        overview = this.overview ?: "",
        posterPath = this.posterPath ?: "",
        isLiked = this.isLiked
    )
}
