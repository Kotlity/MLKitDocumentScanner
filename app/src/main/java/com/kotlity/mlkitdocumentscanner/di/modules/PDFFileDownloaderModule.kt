package com.kotlity.mlkitdocumentscanner.di.modules

import android.content.Context
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.PDFFileDownloader
import com.kotlity.mlkitdocumentscanner.domain.repositories.FileDownloader
import com.kotlity.mlkitdocumentscanner.helpers.dispatchers.AppDispatchers
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderError
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderSuccess
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PDFFileDownloaderModule {

    @Provides
    @Singleton
    fun providePDFFileDownloader(
        @ApplicationContext context: Context,
        appDispatchers: AppDispatchers
    ): FileDownloader<PDFFileDownloaderSuccess, PDFFileDownloaderError> {
        return PDFFileDownloader(
            context = context,
            appDispatchers = appDispatchers
        )
    }
}