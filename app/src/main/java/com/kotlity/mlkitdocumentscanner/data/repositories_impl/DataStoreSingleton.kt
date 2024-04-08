package com.kotlity.mlkitdocumentscanner.data.repositories_impl

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DATA_STORE_PREFERENCES_NAME

class DataStoreSingleton {

    companion object {
        val Context.dataStore by preferencesDataStore(DATA_STORE_PREFERENCES_NAME)
    }
}