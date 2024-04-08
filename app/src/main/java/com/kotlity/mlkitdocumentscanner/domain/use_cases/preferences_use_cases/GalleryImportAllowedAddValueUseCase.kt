package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import javax.inject.Inject

class GalleryImportAllowedAddValueUseCase @Inject constructor(private val galleryImportAllowedManager: PreferencesManager<Boolean>) {

    suspend operator fun invoke(value: Boolean) = galleryImportAllowedManager.addValue(value = value)
}