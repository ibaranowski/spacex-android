package com.spacex.rockets.injection

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spacex.rockets.BuildConfig
import com.spacex.rockets.data.net.RestApi
import com.spacex.rockets.data.net.RestService
import com.spacex.rockets.data.net.RestTransformers
import com.spacex.rockets.data.net.serializer.DateDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
public class DataModule {

    companion object {
        const val API_URL = "apiUrl"
    }


    @Provides
    @Singleton
    fun provideGson(context: Context): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context,
                            logger: HttpLoggingInterceptor?): OkHttpClient {

        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)

        val builder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache)

        if (logger != null) {
            builder.addInterceptor(logger)
        }

        return builder.build()
    }


    @Provides
    @Singleton
    fun provideOkHttpLogger(): HttpLoggingInterceptor? {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return logging
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson,
                        @Named(DataModule.API_URL) apiEndpoint: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(apiEndpoint)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build()

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideRestService(restApi: RestApi, restParsingTransformers: RestTransformers): RestService =
            RestService(restApi, restParsingTransformers)

    @Provides
    @Singleton
    @Named(DataModule.API_URL)
    fun provideApiUrl(): String = BuildConfig.API_ENDPOINT
}