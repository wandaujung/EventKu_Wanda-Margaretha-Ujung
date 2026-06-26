package com.wanda0002.eventku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wanda0002.eventku.repository.EventRepository

class EventViewModelFactory(
    private val repository: EventRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return EventViewModel(repository) as T
    }
}