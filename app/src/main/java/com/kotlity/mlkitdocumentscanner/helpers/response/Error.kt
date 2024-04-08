package com.kotlity.mlkitdocumentscanner.helpers.response

sealed interface Error

sealed class ScannerError(val message: String): Error {
    class DocumentScannerError(message: String): ScannerError(message = message)
    class DocumentScannerCanceled(message: String): ScannerError(message = message)
}

enum class PDFFileDownloaderError: Error {
    IOEXCEPTION,
    FILESYSTEMEXCEPTION,
    OUTOFMEMORYERROR,
    NUMBERFORMATEXCEPTION,
    SECURITYEXCEPTION
}