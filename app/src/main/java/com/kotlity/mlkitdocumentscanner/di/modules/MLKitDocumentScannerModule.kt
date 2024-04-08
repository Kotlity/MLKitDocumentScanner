package com.kotlity.mlkitdocumentscanner.di.modules

import android.content.IntentSender
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.MLKitDocumentScanner
import com.kotlity.mlkitdocumentscanner.domain.repositories.DocumentScanner
import com.kotlity.mlkitdocumentscanner.helpers.dispatchers.AppDispatchers
import com.kotlity.mlkitdocumentscanner.helpers.response.ScannerError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MLKitDocumentScannerModule {

    @Provides
    @Singleton
    fun provideMLKitDocumentScanner(appDispatchers: AppDispatchers): DocumentScanner<IntentSender, ScannerError> {
        return MLKitDocumentScanner(
            appDispatchers = appDispatchers
        )
    }
}