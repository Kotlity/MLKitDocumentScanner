package com.kotlity.documentscanner.data.repositories_impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DataStorePreferencesManager<T>: PreferencesManager<T> {

    val key: Preferences.Key<T>

    suspend fun insertData(dataStore: DataStore<Preferences>, data: T) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[key] = data
        }
    }

    fun retrieveData(dataStore: DataStore<Preferences>, defaultData: T): Flow<T> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: defaultData
        }
    }
}