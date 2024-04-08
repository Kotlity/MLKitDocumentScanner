package com.kotlity.mlkitdocumentscanner.data.repositories_impl

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.kotlity.documentscanner.data.repositories_impl.DataStorePreferencesManager
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.DataStoreSingleton.Companion.dataStore
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DEFAULT_GALLERY_IMPORT_ALLOWED
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.GALLERY_IMPORT_ALLOWED_KEY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryImportAllowedManager @Inject constructor(private val context: Context):
    DataStorePreferencesManager<Boolean> {

    override val key: Preferences.Key<Boolean> = booleanPreferencesKey(GALLERY_IMPORT_ALLOWED_KEY)

    override suspend fun addValue(value: Boolean) {
         insertData(dataStore = context.dataStore, data = value)
     }

     override fun getValue(): Flow<Boolean> {
         return retrieveData(dataStore = context.dataStore, defaultData = DEFAULT_GALLERY_IMPORT_ALLOWED)
     }

}