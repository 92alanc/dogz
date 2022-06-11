package com.alancamargo.lystchallenge.core.network

import com.alancamargo.lystchallenge.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val HEADER_API_KEY = "x-api-key"
private const val MEDIA_TYPE = "application/json"

@OptIn(ExperimentalSerializationApi::class)
class HttpClient {

    fun <T> getService(serviceClass: Class<T>): T {
        val mediaType = MEDIA_TYPE.toMediaType()
        val converterFactory = Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(mediaType)

        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .client(getClient())
            .build()
            .create(serviceClass)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getHeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    private fun getHeaderInterceptor(): (Interceptor.Chain) -> Response = { chain ->
        val request = chain.request().newBuilder()
            .addHeader(HEADER_API_KEY, BuildConfig.API_KEY)
            .build()

        chain.proceed(request)
    }

}
