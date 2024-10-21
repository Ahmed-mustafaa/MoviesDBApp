package banquemisr.challenge05.moviesdbapp.Model.Database

import androidx.room.Entity
import banquemisr.challenge05.moviesdbapp.Model.Genre

@Entity(tableName = "movieEntity")
data class MovieEntity(
    val adult: Boolean,
    val backdrop_path: String?,
    val genres: List<Genre>, // You need genres specifically for details
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val runtime: Int?,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)
