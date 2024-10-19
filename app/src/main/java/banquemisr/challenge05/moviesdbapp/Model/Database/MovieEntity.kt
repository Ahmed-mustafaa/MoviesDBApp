package banquemisr.challenge05.moviesdbapp.Model.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "movieEntity")
data class MovieEntity(
    @PrimaryKey val id: Int,            // Movie ID from TMDb
    val title: String,                  // Movie title
    val releaseDate: String,            // Release date of the movie
    val posterPath: String?,            // Path to the movie's poster image
    val overview: String,               // Movie overview/description
    val voteAverage1: String,
    val voteAverage: Double
)