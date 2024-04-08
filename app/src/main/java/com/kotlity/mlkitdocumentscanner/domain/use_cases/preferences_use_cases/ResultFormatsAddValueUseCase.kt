package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.di.qualifiers.ResultFormatsQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import javax.inject.Inject

class ResultFormatsAddValueUseCase @Inject constructor(@ResultFormatsQualifier private val resultFormatsManager: PreferencesManager<Int>) {

    suspend operator fun invoke(value: Int) = resultFormatsManager.addValue(value)
}