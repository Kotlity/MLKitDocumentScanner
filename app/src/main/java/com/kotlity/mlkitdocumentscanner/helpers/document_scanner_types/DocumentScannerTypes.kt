package com.kotlity.mlkitdocumentscanner.helpers.document_scanner_types

enum class ResultScannerFormats(val value: Int) {
    JPEG(101), PDF(102), JPEG_PDF(101102)
}

enum class ScannerModes(val value: Int) {
    FULL(1), BASE_WITH_FILTER(2), BASE(3)
}