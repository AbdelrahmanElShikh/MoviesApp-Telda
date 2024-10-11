package com.telda.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.data.datasource.local
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM ${DbConstants.WATCHLIST_TABLE}")
    fun getMovies(): List<DbMovie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: DbMovie)

    @Delete
    fun deleteMovie(movie: DbMovie)
}
