package com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases

import com.kotlity.mlkitdocumentscanner.di.qualifiers.PageLimitQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PageLimitGetValueUseCase @Inject constructor(@PageLimitQualifier private val pageLimitManager: PreferencesManager<Int>) {

    operator fun invoke(): Flow<Int> = pageLimitManager.getValue()
}