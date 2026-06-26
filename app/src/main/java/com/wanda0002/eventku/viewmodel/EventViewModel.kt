package com.wanda0002.eventku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wanda0002.eventku.data.Event
import com.wanda0002.eventku.repository.EventRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import com.wanda0002.eventku.data.remote.EventResponse

class EventViewModel(
    private val repository: EventRepository
) : ViewModel() {

    val events =
        repository.allEvents.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun insert(event: Event) {
        viewModelScope.launch {
            repository.insert(event)
        }
    }

    fun update(event: Event) {
        viewModelScope.launch {
            repository.update(event)
        }
    }

    fun delete(event: Event) {
        viewModelScope.launch {
            repository.delete(event)
        }
    }

    fun syncApiEvents() {

        viewModelScope.launch {

            try {

                repository.syncApiToRoom()

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }

    suspend fun getEventById(
        id: Int
    ): Event? {

        return repository.getById(id)
    }

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading =
        _isLoading.asStateFlow()


    fun syncPendingEvents() {

        viewModelScope.launch {

            repository.syncPendingEvents()
        }
    }
}






