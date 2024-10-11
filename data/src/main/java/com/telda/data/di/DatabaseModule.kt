package com.telda.data.di

import android.content.Context
import androidx.room.Room
import com.telda.data.datasource.local.DbConstants
import com.telda.data.datasource.local.MoviesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.data.di
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideMoviesDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MoviesDb::class.java, DbConstants.DB).build()

    @Provides
    fun provideMoviesDao(moviesDb: MoviesDb) = moviesDb.movieDao()

}
