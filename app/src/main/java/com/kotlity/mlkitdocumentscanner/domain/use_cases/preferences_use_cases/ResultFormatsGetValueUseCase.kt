package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.di.qualifiers.ResultFormatsQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResultFormatsGetValueUseCase @Inject constructor(@ResultFormatsQualifier private val resultFormatsManager: PreferencesManager<Int>) {

    operator fun invoke(): Flow<Int> = resultFormatsManager.getValue()
}