package com.wanda0002.eventku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wanda0002.eventku.datastore.SessionManager
import com.wanda0002.eventku.repository.AuthRepository

class LoginViewModelFactory(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                LoginViewModel::class.java
            )
        ) {

            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(
                repository,
                sessionManager
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel"
        )
    }
}