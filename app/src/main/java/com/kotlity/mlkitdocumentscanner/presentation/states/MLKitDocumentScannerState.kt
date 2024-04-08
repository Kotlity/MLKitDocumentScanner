package com.kotlity.mlkitdocumentscanner.presentation.states

import android.net.Uri
import androidx.annotation.IntRange

data class MLKitDocumentScannerState(
    val scannerImages: List<Uri> = emptyList(),
    @IntRange(from = 1, to = 10)
    val currentPageNumberLimit: Int = 5,
    val isAlertDialogVisible: Boolean = false,
    val pdfFileDownloadState: PDFFileDownloadState = PDFFileDownloadState(),
    val scannerResult: ScannerResult = ScannerResult()
)