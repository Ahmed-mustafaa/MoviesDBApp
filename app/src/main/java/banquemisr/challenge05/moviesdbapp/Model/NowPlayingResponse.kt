package banquemisr.challenge05.moviesdbapp.Model

data class NowPlayingResponse(
val page: Int,
val results: List<Movie>,  // List of movies
val total_pages: Int,
val total_results: Int
)

