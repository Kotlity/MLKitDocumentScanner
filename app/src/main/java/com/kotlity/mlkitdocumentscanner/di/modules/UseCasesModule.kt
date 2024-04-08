package com.kotlity.mlkitdocumentscanner.di.modules

import android.content.IntentSender
import com.kotlity.mlkitdocumentscanner.di.qualifiers.PageLimitQualifier
import com.kotlity.mlkitdocumentscanner.di.qualifiers.ResultFormatsQualifier
import com.kotlity.mlkitdocumentscanner.di.qualifiers.ScannerModeQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.DocumentScanner
import com.kotlity.mlkitdocumentscanner.domain.repositories.FileDownloader
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import com.kotlity.mlkitdocumentscanner.domain.use_cases.AppUseCases
import com.kotlity.mlkitdocumentscanner.domain.use_cases.document_scanner_use_case.MLKitScanDocumentUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.file_downloader_use_case.PDFFileDownloaderUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.GalleryImportAllowedAddValueUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.GalleryImportAllowedGetValueUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.PageLimitAddValueUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.PageLimitGetValueUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.ResultFormatsAddValueUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.ResultFormatsGetValueUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.ScannerModeAddValueUseCase
import com.kotlity.mlkitdocumentscanner.domain.use_cases.preferences_use_cases.ScannerModeGetValueUseCase
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderError
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderSuccess
import com.kotlity.mlkitdocumentscanner.helpers.response.ScannerError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideMLKitScanDocumentUseCase(
        documentScanner: DocumentScanner<IntentSender, ScannerError>
    ) = MLKitScanDocumentUseCase(
        documentScanner = documentScanner
    )

    @Provides
    @Singleton
    fun providePDFFileDownloaderUseCase(
        fileDownloader: FileDownloader<PDFFileDownloaderSuccess, PDFFileDownloaderError>
    ) = PDFFileDownloaderUseCase(
        fileDownloader = fileDownloader
    )

    @Provides
    @Singleton
    fun provideGalleryImportAllowedAddValueUseCase(
        galleryImportAllowedManager: PreferencesManager<Boolean>
    ) = GalleryImportAllowedAddValueUseCase(galleryImportAllowedManager = galleryImportAllowedManager)

    @Provides
    @Singleton
    fun provideGalleryImportAllowedGetValueUseCase(
        galleryImportAllowedManager: PreferencesManager<Boolean>
    ) = GalleryImportAllowedGetValueUseCase(galleryImportAllowedManager = galleryImportAllowedManager)

    @Provides
    @Singleton
    @PageLimitQualifier
    fun providePageLimitAddValueUseCase(
        pageLimitManager: PreferencesManager<Int>
    ) = PageLimitAddValueUseCase(pageLimitManager = pageLimitManager)

    @Provides
    @Singleton
    @PageLimitQualifier
    fun providePageLimitGetValueUseCase(
        pageLimitManager: PreferencesManager<Int>
    ) = PageLimitGetValueUseCase(pageLimitManager = pageLimitManager)

    @Provides
    @Singleton
    @ResultFormatsQualifier
    fun provideResultFormatsAddValueUseCase(
        resultFormatsManager: PreferencesManager<Int>
    ) = ResultFormatsAddValueUseCase(resultFormatsManager = resultFormatsManager)

    @Provides
    @Singleton
    @ResultFormatsQualifier
    fun provideResultFormatsGetValueUseCase(
        resultFormatsManager: PreferencesManager<Int>
    ) = ResultFormatsGetValueUseCase(resultFormatsManager = resultFormatsManager)

    @Provides
    @Singleton
    @ScannerModeQualifier
    fun provideScannerModeAddValueUseCase(
        scannerModeManager: PreferencesManager<Int>
    ) = ScannerModeAddValueUseCase(scannerModeManager = scannerModeManager)

    @Provides
    @Singleton
    @ScannerModeQualifier
    fun provideScannerModeGetValueUseCase(
        scannerModeManager: PreferencesManager<Int>
    ) = ScannerModeGetValueUseCase(scannerModeManager = scannerModeManager)

    @Provides
    @Singleton
    fun provideAppUseCases(
        mlKitScanDocumentUseCase: MLKitScanDocumentUseCase,
        pdfFileDownloaderUseCase: PDFFileDownloaderUseCase,
        galleryImportAllowedAddValueUseCase: GalleryImportAllowedAddValueUseCase,
        galleryImportAllowedGetValueUseCase: GalleryImportAllowedGetValueUseCase,
        pageLimitAddValueUseCase: PageLimitAddValueUseCase,
        pageLimitGetValueUseCase: PageLimitGetValueUseCase,
        resultFormatsAddValueUseCase: ResultFormatsAddValueUseCase,
        resultFormatsGetValueUseCase: ResultFormatsGetValueUseCase,
        scannerModeAddValueUseCase: ScannerModeAddValueUseCase,
        scannerModeGetValueUseCase: ScannerModeGetValueUseCase
    ) = AppUseCases(
        mlKitScanDocumentUseCase = mlKitScanDocumentUseCase,
        pdfFileDownloaderUseCase = pdfFileDownloaderUseCase,
        galleryImportAllowedAddValueUseCase = galleryImportAllowedAddValueUseCase,
        galleryImportAllowedGetValueUseCase = galleryImportAllowedGetValueUseCase,
        pageLimitAddValueUseCase = pageLimitAddValueUseCase,
        pageLimitGetValueUseCase = pageLimitGetValueUseCase,
        resultFormatsAddValueUseCase = resultFormatsAddValueUseCase,
        resultFormatsGetValueUseCase = resultFormatsGetValueUseCase,
        scannerModeAddValueUseCase = scannerModeAddValueUseCase,
        scannerModeGetValueUseCase = scannerModeGetValueUseCase
    )
}