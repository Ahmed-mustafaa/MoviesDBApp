package banquemisr.challenge05.moviesdbapp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import banquemisr.challenge05.moviesdbapp.Model.Movie
import banquemisr.challenge05.moviesdbapp.Model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMovie(movie: Movie)
     @Delete
     fun deleteMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>
    @Query("SELECT * FROM movies")
    fun getNowPlayingMovies(): List<Movie>

    // New method to insert a movie by its ID
    @Query("INSERT INTO movies (id) VALUES (:movieId)")
    suspend fun insertMovieById(movieId: Int)
  /*  @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieEntity(movie: MovieEntity)*/

    @Query("SELECT * FROM movies WHERE id = :movieID LIMIT 1")
     fun getMovieById(movieID: Int): Flow<MovieEntity>
}
