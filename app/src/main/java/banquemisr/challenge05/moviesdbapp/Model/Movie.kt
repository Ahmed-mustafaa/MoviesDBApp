package banquemisr.challenge05.moviesdbapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    @PrimaryKey val id: Int,
    val budget: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    // Extension function to convert a list of genres to a string
    companion object {
        fun List<Genre>.toGenreString(): String {
            return this.joinToString(",") { it.name }
        }
    }
}
