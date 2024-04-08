package com.kotlity.mlkitdocumentscanner.domain.use_cases.document_scanner_use_case

import android.content.Context
import android.content.IntentSender
import com.kotlity.mlkitdocumentscanner.helpers.response.ScannerError
import com.kotlity.mlkitdocumentscanner.domain.repositories.DocumentScanner
import javax.inject.Inject

class MLKitScanDocumentUseCase @Inject constructor(private val documentScanner: DocumentScanner<IntentSender, ScannerError>) {

    suspend operator fun invoke(
        galleryImportAllowed: Boolean,
        pageLimit: Int,
        resultFormats: Int,
        scannerMode: Int,
        context: Context,
        onSuccess: (IntentSender) -> Unit,
        onFailure: (ScannerError) -> Unit
    ) {
        documentScanner.scanDocument(
            galleryImportAllowed = galleryImportAllowed,
            pageLimit = pageLimit,
            resultFormats = resultFormats,
            scannerMode = scannerMode,
            context = context,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}