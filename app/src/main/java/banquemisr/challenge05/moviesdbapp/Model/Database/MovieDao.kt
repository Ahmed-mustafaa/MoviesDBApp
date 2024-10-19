package banquemisr.challenge05.moviesdbapp.Model.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import banquemisr.challenge05.moviesdbapp.Model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
    interface MovieDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
         fun insertMovie(movie: Movie)

        @Insert(onConflict = OnConflictStrategy.IGNORE)
         fun insertMovies(movies: List<Movie>)

        @Query("SELECT * FROM movies")
        fun getAllMovies(): Flow<List<Movie>>

        @Delete
         fun deleteMovie(movie: Movie)

       /* @Query("SELECT * FROM movies WHERE id = :movieId")
         fun getMovieById(movieId: Int): MovieEntity?*/
    }