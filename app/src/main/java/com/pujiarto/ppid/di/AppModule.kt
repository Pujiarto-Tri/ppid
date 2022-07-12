package com.pujiarto.ppid.di

import android.app.Application
import androidx.room.Room
import com.pujiarto.ppid.data.local.DocumentDatabase
import com.pujiarto.ppid.data.remote.DocumentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDocumentApi(): DocumentApi {
        return Retrofit.Builder()
            .baseUrl(DocumentApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDocumentDatabase(app: Application): DocumentDatabase {
        return Room.databaseBuilder(
            app,
            DocumentDatabase::class.java,
            "documentdb.db"
        ).build()
    }
}