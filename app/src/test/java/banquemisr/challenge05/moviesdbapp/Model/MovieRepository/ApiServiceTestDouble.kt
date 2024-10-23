package banquemisr.challenge05.moviesdbapp.Model.MovieRepository

import banquemisr.challenge05.moviesdbapp.Model.Movie
import banquemisr.challenge05.moviesdbapp.Model.MovieEntity
import banquemisr.challenge05.moviesdbapp.Model.Genre
import banquemisr.challenge05.moviesdbapp.Service.ApiResponse
import banquemisr.challenge05.moviesdbapp.Service.ApiService

class ApiServiceTestDouble:ApiService {

    private val movies = listOf(
        Movie(false,
            "Test path",
            5,120,
            "EN",
            "OTitle",
            "Original Title",0.0,
            0.0.toString(),"Poster/test",
            12000000,0,"Test Title",
            false,
            00.0,1
    ),
        Movie(
            adult = false,
            backdrop_path = "/backdrop2.jpg",
            id = 2,
            budget = 2000000,
            original_language = "en",
            original_title = "Movie Two",
            overview = "Overview of movie two",
            popularity = 20.0,
            poster_path = "/poster2.jpg",
            release_date = "2023-05-05",
            revenue = 4000000,
            runtime = 130,
            title = "Movie Two",
            video = false,
            vote_average = 7.0,
            vote_count = 300
        )
    )
    override suspend fun getNowPlayingMovies(apiKey: String): ApiResponse {
        return ApiResponse(1,movies,1,movies.size)
    }
    override suspend fun getUpcomingMovies(apiKey: String): ApiResponse {
        return ApiResponse(1,movies,1,movies.size)
    }

    override suspend fun getPopularMovies(apiKey: String): ApiResponse {
        return ApiResponse(1,movies,1,movies.size)
    }

    override suspend fun getMovieDetails(movieId: Int?, apiKey: String): MovieEntity {
        val movie = movies.find { it.id == movieId }?:  throw Exception("Movie not found")
        return MovieEntity(
            adult = false,
            backdrop_path = "/backdrop2.jpg",
            id = 2,
            original_language = "en",
            original_title = "Movie Two",
            overview = "Overview of movie two",
            popularity = 20.0,
            poster_path = "/poster2.jpg",
            release_date = "2023-05-05",
            runtime = 130,
            title = "Movie Two",
            vote_average = 7.0,
            vote_count = 300,
            genres = listOf(Genre(1,"genre1"),
                Genre(2,"genre2")),
            budget = 2000000,
            revenue = 4000000,
            video = false
        )

    }

}