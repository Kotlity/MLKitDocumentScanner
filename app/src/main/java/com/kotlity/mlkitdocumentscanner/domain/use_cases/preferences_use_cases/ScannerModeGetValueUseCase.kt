package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.di.qualifiers.ScannerModeQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScannerModeGetValueUseCase @Inject constructor(@ScannerModeQualifier private val scannerModeManager: PreferencesManager<Int>) {

    operator fun invoke(): Flow<Int> = scannerModeManager.getValue()
}