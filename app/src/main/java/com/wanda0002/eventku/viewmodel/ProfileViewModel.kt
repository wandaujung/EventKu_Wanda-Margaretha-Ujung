package com.wanda0002.eventku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wanda0002.eventku.data.remote.UserResponse
import com.wanda0002.eventku.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class ProfileViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _user =
        MutableStateFlow<UserResponse?>(null)

    val user: StateFlow<UserResponse?> =
        _user

    private val _loading =
        MutableStateFlow(false)

    val loading =
        _loading

    init {
        getProfile()
    }

    private fun getProfile() {

        _user.value =
            UserResponse(
                id = 1,
                firstName = "Admin",
                lastName = "EventKu",
                email = "admin@eventku.com",
                image = "https://dummyjson.com/icon/emilys/128"
            )
    }
}