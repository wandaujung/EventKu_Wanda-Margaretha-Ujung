package com.wanda0002.eventku.data.remote

import retrofit2.http.GET

interface EventApi {

    @GET("posts")
    suspend fun getEvents(): List<EventResponse>

}