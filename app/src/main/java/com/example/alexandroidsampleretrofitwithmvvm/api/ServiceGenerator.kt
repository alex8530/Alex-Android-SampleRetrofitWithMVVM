package com.example.androidnamechk.api

import android.text.TextUtils
import com.example.alexandroidsampleretrofitwithmvvm.api.AuthenticationInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {

    val BASE_URL = APIUtils.BASE_URL


    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create() )

    private var retrofit = builder.build()
    private val httpClient = OkHttpClient.Builder()

//    fun <S> createService(serviceClass: Class<S>): S {
//        return retrofit.create(serviceClass)
//    }


    fun <S> createService(serviceClass: Class<S>): S {

            val interceptor = AuthenticationInterceptor("")
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                //add logging..
                val logger =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(logger)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        return retrofit.create(serviceClass)
    }

    fun <S> createService(serviceClass: Class<S>, authToken: String?): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken)
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                //add logging..
                val logger =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(logger)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }
        return retrofit.create(serviceClass)
    }
}