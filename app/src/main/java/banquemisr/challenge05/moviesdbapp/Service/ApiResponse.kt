package banquemisr.challenge05.moviesdbapp.Service

import banquemisr.challenge05.moviesdbapp.Model.Database.Movie

data class ApiResponse(
    val page: Int,
    val results: List<Movie>,  // List of movies
    val total_pages: Int,
    val total_results: Int
)

