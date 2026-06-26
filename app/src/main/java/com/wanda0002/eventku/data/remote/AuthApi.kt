package com.wanda0002.eventku.data.remote
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface  AuthApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("users/1")
    suspend fun getUser(): UserResponse
}