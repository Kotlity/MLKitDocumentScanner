package com.kotlity.mlkitdocumentscanner.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PreferencesManager<T> {

     suspend fun addValue(value: T)

     fun getValue(): Flow<T>
}