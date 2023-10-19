package com.example.moviepedia.di

import android.content.Context
import com.example.moviepedia.components.Constants
import com.example.moviepedia.data.datastore.LayoutType
import com.example.moviepedia.data.paging.PagingRepositoryImpl
import com.example.moviepedia.data.remote.MoviesApi
import com.example.moviepedia.data.remote.NonPagingApiRepositoryImpl
import com.example.moviepedia.domain.NonPagingApiRepository
import com.example.moviepedia.domain.PagingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideLayoutType(@ApplicationContext context: Context): LayoutType {
        return LayoutType(context)
    }


    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .callTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit):MoviesApi{
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun providePagingRepository(
        moviesApi: MoviesApi
    ):PagingRepository{
        return PagingRepositoryImpl(moviesApi)
    }

    @Provides
    @Singleton
    fun provideNonPagingApiRepository(
        moviesApi: MoviesApi
    ):NonPagingApiRepository{
        return NonPagingApiRepositoryImpl(moviesApi)
    }

}