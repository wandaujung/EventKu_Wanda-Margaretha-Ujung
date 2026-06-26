package com.wanda0002.eventku.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by
preferencesDataStore(
    name = "user_session"
)

class SessionManager(
    private val context: Context
) {

    companion object {

        private val TOKEN =
            stringPreferencesKey("token")
    }

    suspend fun saveToken(
        token: String
    ) {

        context.dataStore.edit {

            it[TOKEN] = token
        }
    }

    val tokenFlow =
        context.dataStore.data.map {

            it[TOKEN] ?: ""
        }

    suspend fun logout() {

        context.dataStore.edit {

            it.clear()
        }
    }
}