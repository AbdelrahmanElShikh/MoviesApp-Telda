package com.telda.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.data.datasource.local
 */
@Database(entities = [DbMovie::class], version = 1, exportSchema = false)
abstract class MoviesDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
