package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.di.qualifiers.ScannerModeQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import javax.inject.Inject

class ScannerModeAddValueUseCase @Inject constructor(@ScannerModeQualifier private val scannerModeManager: PreferencesManager<Int>) {

    suspend operator fun invoke(value: Int) = scannerModeManager.addValue(value)
}