package com.wanda0002.eventku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wanda0002.eventku.datastore.SessionManager
import com.wanda0002.eventku.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginSuccess =
        MutableStateFlow(false)

    val loginSuccess =
        _loginSuccess.asStateFlow()

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading =
        _isLoading.asStateFlow()

    private val _errorMessage =
        MutableStateFlow("")

    val errorMessage =
        _errorMessage.asStateFlow()

    fun login(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = ""

            try {

                if (
                    email == "admin@eventku.com" &&
                    password == "123456"
                ) {

                    sessionManager.saveToken(
                        "EVENTKU_TOKEN"
                    )

                    _loginSuccess.value = true

                } else {

                    _errorMessage.value =
                        "Email atau Password salah"
                }

            } finally {

                _isLoading.value = false
            }
        }
    }
}