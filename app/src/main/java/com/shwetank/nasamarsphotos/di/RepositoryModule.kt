package com.shwetank.nasamarsphotos.di

import com.shwetank.nasamarsphotos.repo.DataRepository
import com.shwetank.nasamarsphotos.repo.Repository
import com.shwetank.nasamarsphotos.repo.network.MarsRoverPhotoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule{

    @Singleton
    @Provides
    fun provideRepository (
        marsRoverPhotoAPI: MarsRoverPhotoAPI
    ): Repository {
        return DataRepository(marsRoverPhotoAPI)
    }

}
