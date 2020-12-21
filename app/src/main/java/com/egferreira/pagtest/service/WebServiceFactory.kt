package com.egferreira.pagtest.service

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import com.egferreira.pagtest.base.Constants
import com.egferreira.pagtest.base.jsonserializer.BigDecimalSerializer
import com.egferreira.pagtest.base.jsonserializer.LocalDateSerializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.LocalDate
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

object WebServiceFactory {

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateSerializer())
        .registerTypeAdapter(BigDecimal::class.java, BigDecimalSerializer())
        .excludeFieldsWithoutExposeAnnotation()
        .setDateFormat(Constants.ISO_8601_24H_FULL_FORMAT)
        .create()


    fun provideOkHttpClient(
    ): OkHttpClient {
        return provideOkHttpClientBuilder().build()
    }

    inline fun <reified T> createService(okHttpClient: OkHttpClient = provideOkHttpClient()): T {
        return defaultRetrofitBuilder(okHttpClient)
            .create(T::class.java)
    }

    fun defaultRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @JvmStatic
    fun <T> createService(serviceClass: Class<T>, okHttpClient: OkHttpClient): T {
        return defaultRetrofitBuilder(okHttpClient)
            .create(serviceClass)
    }

    private fun provideOkHttpClientBuilder(
    ): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(Constants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(Constants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(BODY))

}