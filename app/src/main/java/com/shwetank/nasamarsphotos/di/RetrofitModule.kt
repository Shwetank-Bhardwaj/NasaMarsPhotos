package com.shwetank.nasamarsphotos.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shwetank.nasamarsphotos.util.Constants
import com.shwetank.nasamarsphotos.repo.network.MarsRoverPhotoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGSONBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideMarsRoverImageApi(retrofit: Retrofit): MarsRoverPhotoAPI {
        return retrofit
            .newBuilder()
            .build()
            .create(MarsRoverPhotoAPI::class.java)
    }

}