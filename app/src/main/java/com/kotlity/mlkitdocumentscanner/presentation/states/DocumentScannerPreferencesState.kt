package com.kotlity.mlkitdocumentscanner.presentation.states

data class DocumentScannerPreferencesState(
    val isGalleryImportAllowed: Boolean,
    val pageNumberLimit: Int,
    val resultFormats: Int,
    val scannerMode: Int,
)