package com.wanda0002.eventku.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val EVENT_BASE_URL =
        "https://jsonplaceholder.typicode.com/"

    private const val AUTH_BASE_URL =
        "https://dummyjson.com/"

    val eventApi: EventApi by lazy {

        Retrofit.Builder()
            .baseUrl(EVENT_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(EventApi::class.java)
    }

    val authApi: AuthApi by lazy {

        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(AuthApi::class.java)
    }
}