package com.dreamworks.webteer.network

import com.dreamworks.webteer.repo.MovieRepo
import com.dreamworks.webteer.service.MovieService
import com.dreamworks.webteer.utils.Consts
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val gson = GsonBuilder().setLenient().create()

    private fun getOkHttpClient():OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Consts.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepo(movieService: MovieService):MovieRepo{
        return MovieRepo(movieService)
    }


}