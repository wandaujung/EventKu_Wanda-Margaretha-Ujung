package com.wanda0002.eventku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wanda0002.eventku.repository.AuthRepository

class ProfileViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return ProfileViewModel(
            repository
        ) as T
    }
}