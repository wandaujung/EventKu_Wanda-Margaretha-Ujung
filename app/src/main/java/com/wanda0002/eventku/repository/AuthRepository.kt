package com.wanda0002.eventku.repository

import com.wanda0002.eventku.data.remote.ApiClient
import com.wanda0002.eventku.data.remote.LoginRequest

class AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ) =
        ApiClient.authApi.login(
            LoginRequest(
                email,
                password
            )
        )

    suspend fun getUser() =
        ApiClient.authApi.getUser()
}