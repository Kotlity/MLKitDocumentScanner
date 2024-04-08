package com.kotlity.mlkitdocumentscanner.presentation.states

import android.net.Uri

data class PDFFileDownloadState(
    val isDownloading: Boolean = false,
    val isAlreadyDownloadPDFFile: Boolean = false,
    val pdfFileUri: Uri = Uri.EMPTY
)
