package com.telda.data.di

import com.telda.data.datasource.local.MovieDao
import com.telda.data.datasource.local.MoviesLocalDataSource
import com.telda.data.datasource.local.MoviesLocalDataSourceImpl
import com.telda.data.datasource.remote.MoviesRemoteDataSource
import com.telda.data.datasource.remote.MoviesRemoteDataSourceImpl
import com.telda.data.remote.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.data.di
 */
@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideMoviesRemoteDataSource(apiService: MovieApiService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideMoviesLocalDataSource(movieDao: MovieDao): MoviesLocalDataSource =
        MoviesLocalDataSourceImpl(movieDao)
}
