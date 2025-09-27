package com.calyrsoft.ucbp1.features.movie.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class MovieEntity {
    @PrimaryKey
    var id: Int = 0
    var title: String? = null
    var overview: String? = null
    var posterPath: String? = null
    var isLiked: Boolean = false
}
