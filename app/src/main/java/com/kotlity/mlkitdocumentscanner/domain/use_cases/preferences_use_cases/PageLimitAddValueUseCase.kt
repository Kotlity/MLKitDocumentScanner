package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.di.qualifiers.PageLimitQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import javax.inject.Inject

class PageLimitAddValueUseCase @Inject constructor(@PageLimitQualifier private val pageLimitManager: PreferencesManager<Int>) {

    suspend operator fun invoke(value: Int) = pageLimitManager.addValue(value)
}