package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryImportAllowedGetValueUseCase @Inject constructor(private val galleryImportAllowedManager: PreferencesManager<Boolean>) {

    operator fun invoke(): Flow<Boolean> = galleryImportAllowedManager.getValue()
}