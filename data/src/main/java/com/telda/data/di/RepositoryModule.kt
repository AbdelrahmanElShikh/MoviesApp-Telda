package com.telda.data.di

import com.telda.data.datasource.MoviesRemoteDataSource
import com.telda.data.repository.MoviesRepositoryImpl
import com.telda.domain.repository.MoviesRepository
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
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(remoteDataSource: MoviesRemoteDataSource): MoviesRepository =
        MoviesRepositoryImpl(remoteDataSource)

}
