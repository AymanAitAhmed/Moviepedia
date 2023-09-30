package com.example.moviepedia.di

import android.content.Context
import com.example.moviepedia.data.LayoutType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideLayoutType(@ApplicationContext context: Context): LayoutType {
        return LayoutType(context)
    }

}