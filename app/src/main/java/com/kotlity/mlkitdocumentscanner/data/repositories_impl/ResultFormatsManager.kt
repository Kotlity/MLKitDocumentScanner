package com.kotlity.mlkitdocumentscanner.data.repositories_impl

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.kotlity.documentscanner.data.repositories_impl.DataStorePreferencesManager
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.DataStoreSingleton.Companion.dataStore
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DEFAULT_RESULT_FORMATS
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.RESULT_FORMATS_KEY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResultFormatsManager @Inject constructor(private val context: Context):
    DataStorePreferencesManager<Int> {

    override val key: Preferences.Key<Int> = intPreferencesKey(RESULT_FORMATS_KEY)

    override suspend fun addValue(value: Int) {
         insertData(dataStore = context.dataStore, data = value)
     }

     override fun getValue(): Flow<Int> {
         return retrieveData(dataStore = context.dataStore, defaultData = DEFAULT_RESULT_FORMATS)
     }

}