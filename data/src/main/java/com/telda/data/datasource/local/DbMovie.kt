package com.telda.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.data.datasource.local
 */
@Entity(DbConstants.WATCHLIST_TABLE)
data class DbMovie(
    @PrimaryKey
    val id: Int
)
