package banquemisr.challenge05.moviesdbapp.Model.MovieRepository

import banquemisr.challenge05.moviesdbapp.Model.Database.Movie
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieRepository{
    suspend fun getNowPlayingMovies(apiKey:String):Flow<List<Movie>>
    suspend fun getPopularMovies(apiKey:String): Flow<List<Movie>>
    suspend fun getUpComingMovies(apiKey:String): Flow<List<Movie>>
    suspend fun insertMovie(movie: Movie)
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getCachedMovies(): Flow<List<Movie>>
    suspend fun insertMovieEntity(movie:Movie)

    suspend fun deleteMovie(movie: Movie)
    suspend fun getMovieDetailsByID(movieID: Int?, apikey: String): Flow<MovieEntity?>

}
