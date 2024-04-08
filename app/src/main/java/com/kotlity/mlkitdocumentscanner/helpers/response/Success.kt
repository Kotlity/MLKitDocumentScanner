package com.kotlity.mlkitdocumentscanner.helpers.response

sealed interface Success

enum class PDFFileDownloaderSuccess: Success {
    DOWNLOADSUCCESS
}