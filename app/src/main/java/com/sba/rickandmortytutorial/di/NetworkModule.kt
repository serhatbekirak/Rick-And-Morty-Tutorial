package com.sba.rickandmortytutorial.di

import com.google.gson.GsonBuilder
import com.sba.rickandmortytutorial.BuildConfig
import com.sba.rickandmortytutorial.data.network.ApiService
import com.sba.rickandmortytutorial.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Singleton
  @Provides
  fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .apply {
        readTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
        addLoggingInterceptor()
      }
      .build()
  }

  @Singleton
  @Provides
  fun provideConverterFactory(): GsonConverterFactory {
    val gson = GsonBuilder()
      .setLenient()
      //.registerTypeAdapter(ClassName::class.java, ClassNameDeserializer()) //Example
      .create()
    return GsonConverterFactory.create(gson)
  }

  @Singleton
  @Provides
  fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(gsonConverterFactory)
      .build()
  }

  @Singleton
  @Provides
  fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

private fun OkHttpClient.Builder.addLoggingInterceptor() {
  val logging = HttpLoggingInterceptor()
  logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
  addInterceptor(logging)
}