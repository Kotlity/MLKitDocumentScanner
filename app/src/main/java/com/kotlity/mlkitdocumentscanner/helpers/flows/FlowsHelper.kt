package com.kotlity.mlkitdocumentscanner.helpers.flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.toStateFlow(
    scope: CoroutineScope,
    stopTimeoutMillis: Long = 5000,
    started: SharingStarted = SharingStarted.WhileSubscribed(stopTimeoutMillis = stopTimeoutMillis),
    initialValue: T
): StateFlow<T> {
    return stateIn(
        scope = scope,
        started = started,
        initialValue = initialValue
    )
}