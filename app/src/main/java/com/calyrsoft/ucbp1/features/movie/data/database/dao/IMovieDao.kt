import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies ORDER BY isLiked DESC")
    fun getMoviesSorted(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies")
    suspend fun getMoviesSortedOnce(): List<MovieEntity>
}
